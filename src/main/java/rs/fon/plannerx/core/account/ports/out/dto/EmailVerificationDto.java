package rs.fon.plannerx.core.account.ports.out.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class EmailVerificationDto extends SelfValidating<EmailVerificationDto> {

    @NotNull
    @NotBlank
    String token;

    @NotNull
    @NotBlank
    String sendTo;

    public EmailVerificationDto(String token, String sendTo) {
        this.token = token;
        this.sendTo = sendTo;
        this.validateSelf();
    }
}
