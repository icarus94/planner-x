package rs.fon.plannerx.application.web.error;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;
import rs.fon.plannerx.common.WebAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@WebAdapter
@Controller
@RequiredArgsConstructor
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    private final FlashMessageFactory flashMessageFactory;

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

    @ResponseBody
    @RequestMapping("/error-json")
    public ResponseEntity<Object> jsonError(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put(
                Message.PLACEHOLDER,
                this.flashMessageFactory.dangerFlashMessage((String) request.getAttribute("error"))
        );
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
