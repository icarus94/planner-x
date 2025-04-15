package rs.fon.plannerx.core.account.ports.in.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class VerifyEmailDto extends SelfValidating<VerifyEmailDto> {

    @NotNull
    @NotBlank
    String token;

    public VerifyEmailDto(String token) {
        this.token = token;
        this.validateSelf();
    }
}
