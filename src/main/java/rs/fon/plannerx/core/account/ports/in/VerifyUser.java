package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.ports.in.dto.VerifyEmailDto;

public interface VerifyUser {
    void verify(VerifyEmailDto verifyEmailDto);
}
