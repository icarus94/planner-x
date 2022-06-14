package rs.fon.plannerx.core.mail.ports.out;

import rs.fon.plannerx.core.mail.ports.out.dto.HtmlContextDto;

public interface GetInvitationHtml {
    String get(HtmlContextDto htmlContextDto);
}
