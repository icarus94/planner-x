package rs.fon.plannerx.core.mail.ports.out;

import rs.fon.plannerx.core.mail.ports.out.dto.EmailContextDto;

public interface SendEmail {
    void send(EmailContextDto emailContextDto);
}
