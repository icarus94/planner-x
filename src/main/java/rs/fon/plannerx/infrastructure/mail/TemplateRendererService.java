package rs.fon.plannerx.infrastructure.mail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import rs.fon.plannerx.core.mail.ports.out.GetInvitationHtml;
import rs.fon.plannerx.core.mail.ports.out.dto.HtmlContextDto;

@Service
@AllArgsConstructor
public class TemplateRendererService implements GetInvitationHtml {

    private final TemplateEngine templateEngine;

    @Override
    public String get(HtmlContextDto htmlContextDto) {
        Context context = new Context();
        context.setVariable("user", htmlContextDto.getUser());
        return templateEngine.process("emails/invite_template01", context);
    }
}
