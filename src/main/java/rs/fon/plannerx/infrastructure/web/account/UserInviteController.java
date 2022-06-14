package rs.fon.plannerx.infrastructure.web.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.mail.ports.in.SendInvitation;
import rs.fon.plannerx.core.mail.ports.in.dto.InvitationDto;
import rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory;
import rs.fon.plannerx.infrastructure.web.messages.Message;
import rs.fon.plannerx.infrastructure.web.security.CurrentUser;
import rs.fon.plannerx.infrastructure.web.security.UserPrincipal;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import java.io.IOException;

@WebAdapter
@Controller
@AllArgsConstructor
public class UserInviteController {

    private final SendInvitation sendInvitationUseCase;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.INVITE_USER)
    public void getPage() {
    }

    @PostMapping(SiteMap.INVITE_USER)
    public void inviteAction(
            @CurrentUser UserPrincipal userPrincipal,
            @Email @RequestParam("invite_email") String email,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response
    ) throws IOException {
        sendInvitationUseCase.send(new InvitationDto(userPrincipal.getId(), email));
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.successFlashMessage(Message.USER_INVITED)
        );
        response.sendRedirect(SiteMap.INVITE_USER);
    }
}
