package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.ports.out.dto.EmailVerificationDto;

public interface SendEmailVerification {
    void sendEmailVerification(EmailVerificationDto emailVerificationDto);
}
