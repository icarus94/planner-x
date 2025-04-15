package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.ports.in.dto.InvitationDto;

public interface SendInvitation {
    void invite(InvitationDto invitationDto);
}
