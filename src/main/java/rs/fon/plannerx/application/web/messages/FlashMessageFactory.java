package rs.fon.plannerx.application.web.messages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FlashMessageFactory {

    private final MessageConverter messageConverter;

    public FlashMessage infoFlashMessage(String message) {
        return this.createFlashMessage(FlashMessageType.INFO, message);
    }

    public FlashMessage infoFlashMessage(Collection<String> messages) {
        return this.createFlashMessage(FlashMessageType.INFO, messages);
    }

    public FlashMessage successFlashMessage(String message) {
        return this.createFlashMessage(FlashMessageType.SUCCESS, message);
    }

    public FlashMessage successFlashMessage(Collection<String> messages) {
        return this.createFlashMessage(FlashMessageType.SUCCESS, messages);
    }

    public FlashMessage warningFlashMessage(String message) {
        return this.createFlashMessage(FlashMessageType.WARNING, message);
    }

    public FlashMessage warningFlashMessage(Collection<String> messages) {
        return this.createFlashMessage(FlashMessageType.WARNING, messages);
    }

    public FlashMessage dangerFlashMessage(String message) {
        return this.createFlashMessage(FlashMessageType.DANGER, message);
    }

    public FlashMessage dangerFlashMessage(Collection<String> messages) {
        return this.createFlashMessage(FlashMessageType.DANGER, messages);
    }

    private FlashMessage createFlashMessage(FlashMessageType flashMessageType, String message) {
        List<String> messages = new LinkedList<>();
        messages.add(this.messageConverter.convert(message));
        return new FlashMessage(flashMessageType, messages);
    }

    private FlashMessage createFlashMessage(FlashMessageType flashMessageType, Collection<String> messages) {
        return new FlashMessage(
                flashMessageType,
                messages.stream()
                        .map(this.messageConverter::convert)
                        .collect(Collectors.toList())
        );
    }
}
