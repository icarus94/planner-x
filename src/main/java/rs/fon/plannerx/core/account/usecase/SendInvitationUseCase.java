package rs.fon.plannerx.core.account.usecase;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.exception.UserException;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.SendInvitation;
import rs.fon.plannerx.core.account.ports.in.dto.InvitationDto;
import rs.fon.plannerx.core.account.ports.out.GetInvitationHtml;
import rs.fon.plannerx.core.account.ports.out.SendEmail;
import rs.fon.plannerx.core.account.ports.out.dto.EmailContextDto;
import rs.fon.plannerx.core.account.ports.out.dto.InvitationHtmlContextDto;

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
            throw UserException.invitedUserIsAlreadyRegistered();
        }

        User userInviter = this.getUserService.getById(invitationDto.getUserSenderId());

        String text = this.getInvitationHtmlService.get(new InvitationHtmlContextDto(userInviter));

        this.sendEmailService.send(new EmailContextDto("Invitation", text, invitationDto.getSendingToEmail()));
    }
}
