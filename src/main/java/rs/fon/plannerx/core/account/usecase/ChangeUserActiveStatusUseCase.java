package rs.fon.plannerx.core.account.usecase;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.ChangeUserActiveStatus;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.dto.UserActiveStatusDto;
import rs.fon.plannerx.core.account.ports.out.UpdateUser;

@UseCase
@AllArgsConstructor
public class ChangeUserActiveStatusUseCase implements ChangeUserActiveStatus {
    private final GetUser getUserService;
    private final UpdateUser updateUserService;

    @Override
    public void change(UserActiveStatusDto userActiveStatusDto) {
        User user = this.getUserService.getUserById(userActiveStatusDto.getId());
        user.setActive(userActiveStatusDto.isActive());
        this.updateUserService.updateUser(user);
    }
}
