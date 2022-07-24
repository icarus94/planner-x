package rs.fon.plannerx.application.web.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;

@WebAdapter
@Controller
public class LoginController {
    @GetMapping(SiteMap.LOGIN)
    public void getPage() {
    }
}
