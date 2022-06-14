package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.ports.in.dto.UserActiveStatusDto;

public interface ChangeUserActiveStatus {
    void change(UserActiveStatusDto userActiveStatusDto);
}
