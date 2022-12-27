package rs.fon.plannerx.core.account.ports.out.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;
import rs.fon.plannerx.core.account.domain.User;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class InvitationHtmlContextDto extends SelfValidating<InvitationHtmlContextDto> {

    @NotNull
    User user;

    String placeholder;

    String templatePath;

    public InvitationHtmlContextDto(User user) {
        this.user = user;
        this.placeholder = "user";
        this.templatePath = "emails/invite_template01";
        this.validateSelf();
    }
}
