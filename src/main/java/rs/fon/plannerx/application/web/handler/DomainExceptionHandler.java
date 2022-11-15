package rs.fon.plannerx.application.web.handler;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;
import rs.fon.plannerx.core.exception.CoreDomainException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@AllArgsConstructor
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {

    private final FlashMessageFactory flashMessageFactory;

    private final Logger logger = LoggerFactory.getLogger(ConstraintExceptionHandler.class);

    @ExceptionHandler(CoreDomainException.class)
    public String domainErrorHandler(HttpServletRequest req, Exception e, RedirectAttributes redirectAttributes) {
        this.logger.error("CoreDomainException Handler ACTIVATED");

        if (("XMLHttpRequest").equals(req.getHeader("X-Requested-With"))) {
            System.out.println("eo ovdde smo");
            req.setAttribute("error", e.getMessage());
            return "forward:/error-json";
        }
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.dangerFlashMessage(e.getMessage())
        );
        String referer = req.getHeader("Referer");
        return "redirect:" + referer;
    }
}
