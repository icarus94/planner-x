package rs.fon.plannerx.core.mail.ports.out.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;
import rs.fon.plannerx.core.account.domain.User;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class HtmlContextDto extends SelfValidating<HtmlContextDto> {

    @NotNull
    User user;

    public HtmlContextDto(User user) {
        this.user = user;
        this.validateSelf();
    }
}
