package rs.fon.plannerx.core.mail.usecase;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.mail.ports.in.SendInvitation;
import rs.fon.plannerx.core.mail.ports.in.dto.InvitationDto;
import rs.fon.plannerx.core.mail.ports.out.GetInvitationHtml;
import rs.fon.plannerx.core.mail.ports.out.SendEmail;
import rs.fon.plannerx.core.mail.ports.out.dto.EmailContextDto;
import rs.fon.plannerx.core.mail.ports.out.dto.HtmlContextDto;

@UseCase
@AllArgsConstructor
public class SendInvitationUseCase implements SendInvitation {

    private final GetUser getUserService;
    private final SendEmail sendEmailService;
    private final GetInvitationHtml getInvitationHtmlService;

    @Override
    public void send(InvitationDto invitationDto) {
        User targetUser = this.getUserService.getByEmail(invitationDto.getSendingToEmail());
        if (targetUser != null) {
            throw new CoreDomainException(CoreDomainException.INVITED_USER_IS_ALREADY_REGISTERED);
        }

        User userInviter = this.getUserService.getById(invitationDto.getUserSenderId());

        String text = this.getInvitationHtmlService.get(new HtmlContextDto(userInviter));

        this.sendEmailService.send(new EmailContextDto("Invitation", text, invitationDto.getSendingToEmail()));
    }
}
