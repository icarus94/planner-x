package rs.fon.plannerx.infrastructure.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.fon.plannerx.core.account.ports.out.SendEmail;
import rs.fon.plannerx.core.account.ports.out.dto.EmailContextDto;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailService implements SendEmail {

    private final JavaMailSender javaMailSender;

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
}
