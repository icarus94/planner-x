package rs.fon.plannerx.infrastructure.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.fon.plannerx.application.config.web.MailConfig;
import rs.fon.plannerx.core.account.ports.out.SendEmail;
import rs.fon.plannerx.core.account.ports.out.SendEmailVerification;
import rs.fon.plannerx.core.account.ports.out.dto.EmailContextDto;
import rs.fon.plannerx.core.account.ports.out.dto.EmailVerificationDto;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailService implements SendEmail, SendEmailVerification {

    private final JavaMailSender javaMailSender;

    private final MailConfig mailConfig;

    @Async
    @Override
    public void send(EmailContextDto emailContextDto) {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom("PlannerX - MyWebPlanner <mywebplanner@plannerx.com>");
            helper.setSubject(emailContextDto.getSubject());
            helper.setText(emailContextDto.getHtml(), true);
            helper.setTo(emailContextDto.getSendTo());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailVerification(EmailVerificationDto emailVerificationDto) {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        String confirmationUrl = this.mailConfig.getHost() + "/account/verify-email?token=" + emailVerificationDto.getToken();

        try {


            helper.setFrom("PlannerX - MyWebPlanner <nikola.1994.trajkovic@gmail.com>");
            helper.setSubject("EMAIL VERIFICATION");
            helper.setText("Click the link to verify your email: " + confirmationUrl);
            helper.setTo(emailVerificationDto.getSendTo());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
