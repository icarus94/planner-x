package rs.fon.plannerx.core.account.usecase;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.UpdateUser;
import rs.fon.plannerx.core.account.ports.in.dto.UpdateUserDto;
import rs.fon.plannerx.core.exception.CoreDomainException;

@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUser {

    private final rs.fon.plannerx.core.account.ports.out.UpdateUser updateUserService;

    private final GetUser getUserService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void update(UpdateUserDto updateUserDto) {
        User user = this.getUserService.getById(updateUserDto.getId());
        if (updateUserDto.getPassword().length() > 0) {
            if (!this.passwordEncoder.matches(updateUserDto.getPassword(), user.getPassword())) {
                throw new CoreDomainException(CoreDomainException.PASSWORD_DOES_NOT_MATCH);
            }
            if (updateUserDto.getNewPassword().length() == 0) {
                throw new CoreDomainException(CoreDomainException.NEW_PASSWORD_IS_BLANK);
            }
            if (!updateUserDto.getNewPassword().equals(updateUserDto.getRetypedPassword())) {
                throw new CoreDomainException(CoreDomainException.NEW_PASSWORDS_DO_NOT_MATCH);
            }
            user.setPassword(this.passwordEncoder.encode(updateUserDto.getNewPassword()));
        }
        user.setName(updateUserDto.getName());
        user.setSurname(updateUserDto.getSurname());
        this.updateUserService.update(user);
    }
}
