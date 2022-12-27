package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.ports.out.dto.EmailContextDto;

public interface SendEmail {
    void send(EmailContextDto emailContextDto);
}
