package rs.fon.plannerx.infrastructure.view;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import rs.fon.plannerx.core.account.ports.out.GetInvitationHtml;
import rs.fon.plannerx.core.account.ports.out.dto.InvitationHtmlContextDto;

@Service
@AllArgsConstructor
public class HtmlTemplateRendererService implements GetInvitationHtml {

    private final TemplateEngine templateEngine;

    @Override
    public String get(InvitationHtmlContextDto invitationHtmlContextDto) {
        Context context = new Context();
        context.setVariable(invitationHtmlContextDto.getPlaceholder(), invitationHtmlContextDto.getUser());
        return templateEngine.process(invitationHtmlContextDto.getTemplatePath(), context);
    }
}
