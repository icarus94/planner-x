package rs.fon.plannerx.infrastructure.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.ChangeUserActiveStatus;
import rs.fon.plannerx.core.account.ports.in.dto.UserActiveStatusDto;
import rs.fon.plannerx.infrastructure.web.messages.Message;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

import java.util.HashMap;
import java.util.Map;

@WebAdapter
@Controller
@AllArgsConstructor
public class UserActiveStatusController {

    private final ChangeUserActiveStatus changeUserActiveStatusService;

    @ResponseBody
    @PostMapping(value = SiteMap.ADMIN_USERS_CHANGE_ACTIVE_STATUS, produces = "application/json")
    public ResponseEntity<Object> checkTaskAction(
            @RequestParam("id") int id,
            @RequestParam(name = "status") boolean status
    ) {
        changeUserActiveStatusService.change(
                new UserActiveStatusDto(
                        id,
                        status
                )
        );
        Map<String, Object> map = new HashMap<>();
        map.put("message", Message.USER_ACTIVE_STATUS_CHANGED);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
