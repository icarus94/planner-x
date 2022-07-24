package rs.fon.plannerx.application.web.messages;

import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

@Value
public class FlashMessage implements Serializable {

    FlashMessageType flashMessageType;

    Collection<String> messages;
}
