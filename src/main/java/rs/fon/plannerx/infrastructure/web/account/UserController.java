package rs.fon.plannerx.infrastructure.web.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.UpdateUser;
import rs.fon.plannerx.core.account.ports.in.dto.UpdateUserDto;
import rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory;
import rs.fon.plannerx.infrastructure.web.messages.Message;
import rs.fon.plannerx.infrastructure.web.security.CurrentUser;
import rs.fon.plannerx.infrastructure.web.security.UserPrincipal;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebAdapter
@Controller
@RequiredArgsConstructor
public class UserController {

    private final GetUser getUserService;

    private final UpdateUser updateUserService;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.ACCOUNT_PAGE)
    public void getPage(
            @CurrentUser UserPrincipal userPrincipal,
            Model model
    ) {
        model.addAttribute("user", getUserService.getUserById(userPrincipal.getId()));
    }

    @PostMapping(SiteMap.ACCOUNT_PAGE)
    public void updateUser(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "newPassword") String newPassword,
            @RequestParam(name = "retypedPassword") String retypedPassword,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response
    ) throws IOException {
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
        response.sendRedirect(SiteMap.ACCOUNT_PAGE);
    }
}
