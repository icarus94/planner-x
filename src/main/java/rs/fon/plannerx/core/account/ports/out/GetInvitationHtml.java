package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.ports.out.dto.InvitationHtmlContextDto;

public interface GetInvitationHtml {
    String get(InvitationHtmlContextDto invitationHtmlContextDto);
}
