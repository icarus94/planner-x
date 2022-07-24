package rs.fon.plannerx.application.web.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.fon.plannerx.application.web.permission.AdminPermission;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.report.ports.out.GetUserAllTaskListReport;

@WebAdapter
@Controller
@AllArgsConstructor
@AdminPermission
public class UserDetailsController {

    private final GetUser getUserService;
    private final GetUserAllTaskListReport getUserAllTaskListReportService;

    @GetMapping(SiteMap.ADMIN_USERS_USER_INFO)
    public void userInfo(
            @RequestParam(name = "id") int userId,
            Model model
    ) {
        model.addAttribute("user", this.getUserService.getById(userId));
        model.addAttribute("userTaskListReport", this.getUserAllTaskListReportService.get(userId));
    }
}
