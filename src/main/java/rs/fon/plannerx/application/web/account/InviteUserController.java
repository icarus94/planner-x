package rs.fon.plannerx.application.web.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;
import rs.fon.plannerx.application.web.permission.AnyAuthenticationPermission;
import rs.fon.plannerx.application.web.security.CurrentUser;
import rs.fon.plannerx.application.web.security.UserPrincipal;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.SendInvitation;
import rs.fon.plannerx.core.account.ports.in.dto.InvitationDto;

import javax.validation.constraints.Email;

@WebAdapter
@Controller
@AllArgsConstructor
@AnyAuthenticationPermission
public class InviteUserController {

    private final SendInvitation sendInvitationUseCase;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.INVITE_USER)
    public void getPage() {
    }

    @PostMapping(SiteMap.INVITE_USER)
    public RedirectView inviteAction(
            @CurrentUser UserPrincipal userPrincipal,
            @Email @RequestParam("invite_email") String email,
            RedirectAttributes redirectAttributes
    ) {
        sendInvitationUseCase.invite(
                new InvitationDto(
                        userPrincipal.getId(),
                        email
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.successFlashMessage(Message.USER_INVITED)
        );
        return new RedirectView(SiteMap.INVITE_USER);
    }
}
