package rs.fon.plannerx.application.web.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;
import rs.fon.plannerx.application.web.permission.RegularUserPermission;
import rs.fon.plannerx.application.web.security.CurrentUser;
import rs.fon.plannerx.application.web.security.UserPrincipal;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.UpdateUser;
import rs.fon.plannerx.core.account.ports.in.dto.UpdateUserDto;

@WebAdapter
@Controller
@RequiredArgsConstructor
@RegularUserPermission
public class UpdateUserController {

    private final GetUser getUserService;

    private final UpdateUser updateUserService;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.ACCOUNT_PAGE)
    public void getPage(
            @CurrentUser UserPrincipal userPrincipal,
            Model model
    ) {
        model.addAttribute("user", getUserService.getById(userPrincipal.getId()));
    }

    @PostMapping(SiteMap.ACCOUNT_PAGE)
    public RedirectView updateUser(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "newPassword") String newPassword,
            @RequestParam(name = "retypedPassword") String retypedPassword,
            RedirectAttributes redirectAttributes
    ) {
        this.updateUserService.update(
                new UpdateUserDto(
                        userPrincipal.getId(),
                        name,
                        surname,
                        password,
                        newPassword,
                        retypedPassword
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.successFlashMessage(Message.USER_EDITED)
        );
        return new RedirectView(SiteMap.ACCOUNT_PAGE);
    }
}
