package rs.fon.plannerx.core.account.usecase;

import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.VerifyUser;
import rs.fon.plannerx.core.account.ports.in.dto.VerifyEmailDto;

@UseCase
@RequiredArgsConstructor
public class VerifyUserUseCase implements VerifyUser {

    private final GetUser getUserService;

    private final rs.fon.plannerx.core.account.ports.out.UpdateUser updateUserService;

    @Override
    public void verify(VerifyEmailDto verifyEmailDto) {
        User user = this.getUserService.getByToken(verifyEmailDto.getToken());

        user.setActive(true);

        updateUserService.update(user);
    }
}
