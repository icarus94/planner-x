package rs.fon.plannerx.application.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rs.fon.plannerx.application.web.permission.AdminPermission;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;

@WebAdapter
@Controller("Admin Homepage")
@AdminPermission
public class HomepageController {
    @GetMapping(SiteMap.ADMIN_HOMEPAGE)
    public void getPage() {
    }
}
