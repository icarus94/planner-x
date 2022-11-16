package rs.fon.plannerx.application.web.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.plannerx.application.web.messages.FlashMessageFactory;
import rs.fon.plannerx.application.web.messages.Message;
import rs.fon.plannerx.application.web.sitemap.SiteMap;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.account.ports.in.RegisterUser;
import rs.fon.plannerx.core.account.ports.in.dto.RegisterUserDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebAdapter
@Controller
@AllArgsConstructor
public class RegisterUserController {

    private final RegisterUser registerUserUseCase;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.REGISTER)
    public void getPage() {
    }

    @PostMapping(SiteMap.REGISTER)
    public void register(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "retypedPassword") String retypedPassword,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response
    ) throws IOException {
        registerUserUseCase.register(
                new RegisterUserDto(
                        name,
                        surname,
                        email,
                        password,
                        retypedPassword
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                flashMessageFactory.successFlashMessage(Message.USER_REGISTERED)
        );
        response.sendRedirect(SiteMap.LOGIN);
    }
}
