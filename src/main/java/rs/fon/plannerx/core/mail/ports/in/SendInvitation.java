package rs.fon.plannerx.core.mail.ports.in;

import rs.fon.plannerx.core.mail.ports.in.dto.InvitationDto;

public interface SendInvitation {
    void send(InvitationDto invitationDto);
}
