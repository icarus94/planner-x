package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.ports.in.dto.UpdateUserDto;

public interface UpdateUser {
    void update(UpdateUserDto updateUserDto);
}
