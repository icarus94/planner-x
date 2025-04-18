package rs.fon.plannerx.application.config.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mail.feature")
@Getter
@Setter
public class MailConfig {
    private boolean enabled;

    private String host;
}
