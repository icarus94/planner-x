package rs.fon.plannerx.core.mail.ports.in.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class InvitationDto extends SelfValidating<InvitationDto> {

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userSenderId;

    @NotNull
    @NotBlank
    String sendingToEmail;

    public InvitationDto(int userSenderId, String sendingToEmail) {
        this.userSenderId = userSenderId;
        this.sendingToEmail = sendingToEmail;
        this.validateSelf();
    }
}
