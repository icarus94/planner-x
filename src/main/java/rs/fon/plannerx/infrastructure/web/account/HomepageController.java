package rs.fon.plannerx.infrastructure.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

@WebAdapter
@Controller
public class HomepageController {
    @GetMapping(SiteMap.HOMEPAGE)
    public void getPage() {
    }
}
