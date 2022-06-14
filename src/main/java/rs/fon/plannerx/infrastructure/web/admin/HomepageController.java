package rs.fon.plannerx.infrastructure.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

@WebAdapter
@Controller("Admin Homepage")
public class HomepageController {
    @GetMapping(SiteMap.ADMIN_HOMEPAGE)
    public void getPage() {
    }
}
