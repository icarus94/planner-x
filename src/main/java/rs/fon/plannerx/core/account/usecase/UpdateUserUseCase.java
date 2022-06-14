package rs.fon.plannerx.core.account.usecase;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.UpdateUser;
import rs.fon.plannerx.core.account.ports.in.dto.UpdateUserDto;

@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUser {

    private final rs.fon.plannerx.core.account.ports.out.UpdateUser updateUserService;

    private final GetUser getUserService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void update(UpdateUserDto updateUserDto) {
        User user = this.getUserService.getUserById(updateUserDto.getId());
        if (!this.passwordEncoder.matches(updateUserDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password doesn't match");     // todo refactor
        }
        user.setPassword(this.passwordEncoder.encode(updateUserDto.getPassword()));
        user.setName(updateUserDto.getName());
        user.setSurname(updateUserDto.getSurname());
        this.updateUserService.updateUser(user);
    }
}
