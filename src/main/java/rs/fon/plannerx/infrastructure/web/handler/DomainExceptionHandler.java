package rs.fon.plannerx.infrastructure.web.handler;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory;
import rs.fon.plannerx.infrastructure.web.messages.Message;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@AllArgsConstructor
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {

    private final FlashMessageFactory flashMessageFactory;

    private final Logger logger = LoggerFactory.getLogger(ConstraintExceptionHandler.class);

    @ExceptionHandler(CoreDomainException.class)
    public String domainErrorHandler(HttpServletRequest req, Exception e, RedirectAttributes redirectAttributes) {
        this.logger.error("CoreDomainException Handler ACTIVATED");
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.dangerFlashMessage(e.getMessage())
        );
        String referer = req.getHeader("Referer");
        return "redirect:" + referer;
    }
}
