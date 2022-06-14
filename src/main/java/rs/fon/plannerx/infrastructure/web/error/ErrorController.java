package rs.fon.plannerx.infrastructure.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.fon.plannerx.common.WebAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@WebAdapter
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            System.out.println(statusCode);
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "action/error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "action/error/500";
            }
        }

        return "action/error/general";
    }
}
