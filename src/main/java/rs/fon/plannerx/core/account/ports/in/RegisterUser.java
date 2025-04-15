package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.ports.in.dto.RegisterUserDto;

public interface RegisterUser {
    void register(RegisterUserDto registerUserDto);

    void registerWithEmailConfirmation(RegisterUserDto registerUserDto);
}
