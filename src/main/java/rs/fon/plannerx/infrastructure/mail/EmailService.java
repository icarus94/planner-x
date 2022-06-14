package rs.fon.plannerx.infrastructure.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.fon.plannerx.core.mail.ports.out.SendEmail;
import rs.fon.plannerx.core.mail.ports.out.dto.EmailContextDto;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailService implements SendEmail {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(EmailContextDto emailContextDto) {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject(emailContextDto.getSubject());
            helper.setText(emailContextDto.getText(), true);
            helper.setTo(emailContextDto.getSendTo());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }
}
