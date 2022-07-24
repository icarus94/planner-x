package rs.fon.plannerx.application.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.fon.plannerx.common.WebAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@WebAdapter
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        this.logger.error("An error occurred | status => {}", status);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "action/error/404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "action/error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "action/error/500";
            }
        }
        return "action/error/general";
    }
}
