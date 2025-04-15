package rs.fon.plannerx.application.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.fon.plannerx.application.web.permission.AdminPermission;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUsersWithRegularRole;
import rs.fon.plannerx.core.account.ports.in.dto.PaginationDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebAdapter
@Controller
@AllArgsConstructor
@AdminPermission
public class UsersController {

    private final GetUsersWithRegularRole getUsersService;

    @GetMapping(SiteMap.ADMIN_USERS)
    public void getPage() {
    }

    @ResponseBody
    @GetMapping(SiteMap.ADMIN_USERS_DATATABLES)
    public ResponseEntity<Object> getUsersForDatatables(
            @RequestParam(value = "draw", required = false) int draw,
            @RequestParam(value = "start", required = false) int start,
            @RequestParam(value = "length", required = false, defaultValue = "20") int length,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection
    ) {
        Map<String, Object> result = new HashMap<>();

        Set<User> users = getUsersService.getPaginated(
                new PaginationDto(
                        start / length,
                        length,
                        sortBy,
                        sortDirection
                )
        );
        result.put("data", users);
        result.put("draw", draw);
        result.put("recordsTotal", getUsersService.getTotalCount());
        result.put("recordsFiltered", getUsersService.getTotalCount());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
