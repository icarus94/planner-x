package rs.fon.plannerx.core.mail.ports.out.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class EmailContextDto extends SelfValidating<EmailContextDto> {

    @NotNull
    @NotBlank
    String subject;

    @NotNull
    @NotBlank
    String html;

    @NotNull
    @NotBlank
    String sendTo;

    public EmailContextDto(String subject, String html, String sendTo) {
        this.subject = subject;
        this.html = html;
        this.sendTo = sendTo;
        this.validateSelf();
    }
}
