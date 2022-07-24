package rs.fon.plannerx.core.account.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.domain.UserRole;
import rs.fon.plannerx.core.account.ports.in.RegisterUser;
import rs.fon.plannerx.core.account.ports.in.dto.RegisterUserDto;
import rs.fon.plannerx.core.exception.CoreDomainException;

@UseCase
@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUser {

    private final rs.fon.plannerx.core.account.ports.out.RegisterUser registerUserService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterUserDto registerUserDto) {
        if (registerUserService.isEmailAlreadyUsed(registerUserDto.getEmail())) {
            throw new CoreDomainException(CoreDomainException.EMAIL_IS_ALREADY_USED);
        }
        User user = new User();
        user.setActive(true);
        user.setName(registerUserDto.getName());
        user.setSurname(registerUserDto.getSurname());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setEmail(registerUserDto.getEmail());
        user.setRole(UserRole.ROLE_REGULAR);
        this.registerUserService.register(user);
    }
}
