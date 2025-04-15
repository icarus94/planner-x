package rs.fon.plannerx.application.web.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.VerifyUser;
import rs.fon.plannerx.core.account.ports.in.dto.VerifyEmailDto;

import javax.servlet.http.HttpServletResponse;

@WebAdapter
@Controller
@AllArgsConstructor
public class VerifyEmailController {

    private final VerifyUser verifyUser;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.MAIL_VERIFY)
    public RedirectView getPage(
            @RequestParam String token,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response
    ) {

        this.verifyUser.verify(new VerifyEmailDto(token));

        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.successFlashMessage(Message.USER_MAIL_CONFIRMED)
        );

        return new RedirectView(SiteMap.LOGIN);
    }
}
