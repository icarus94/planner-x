package rs.fon.plannerx.application.web.handler;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ConstraintExceptionHandler {

    private final FlashMessageFactory flashMessageFactory;

    private final Logger logger = LoggerFactory.getLogger(ConstraintExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public String domainErrorHandler(HttpServletRequest req, ConstraintViolationException e, RedirectAttributes redirectAttributes) {
        e.getConstraintViolations()
                .forEach(constraintViolation -> {
                    this.logger.error(System.lineSeparator());
                    this.logger.error(constraintViolation.getMessageTemplate());
                    this.logger.error(constraintViolation.getMessage());
                    this.logger.error(constraintViolation.getConstraintDescriptor().getMessageTemplate());
                    this.logger.error(System.lineSeparator());
                });

        List<String> errorMessages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.dangerFlashMessage(errorMessages)
        );
        String referer = req.getHeader("Referer");
        return "redirect:" + referer;
    }
}
