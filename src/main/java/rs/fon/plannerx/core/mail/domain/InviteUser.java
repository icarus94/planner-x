package rs.fon.plannerx.core.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rs.fon.plannerx.core.account.domain.User;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class InviteUser {
    private final User source;
    private final User target;
}
