package rs.fon.plannerx.application.web.messages;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageConverter {

    private final MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(MessageConverter.class);

    public String convert(String message) {
        this.logger.debug("[i18n] Trying to convert: [{}]", message);
        String resolvedMessage = message;

        try {
            resolvedMessage = this.messageSource.getMessage(message, new Object[0], Locale.ROOT);
        } catch (NoSuchMessageException exception) {
            this.logger.debug("[i18n] Unable to resolve: [{}]", message);
        }

        this.logger.debug("[i18n] Resolved: [{}] -> [{}]", message, resolvedMessage);
        return resolvedMessage;
    }
}
