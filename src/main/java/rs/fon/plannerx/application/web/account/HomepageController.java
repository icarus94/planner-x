package rs.fon.plannerx.application.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rs.fon.plannerx.application.web.permission.RegularUserPermission;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;

@WebAdapter
@Controller
@RegularUserPermission
public class HomepageController {
    @GetMapping(SiteMap.HOMEPAGE)
    public void getPage() {
    }
}
