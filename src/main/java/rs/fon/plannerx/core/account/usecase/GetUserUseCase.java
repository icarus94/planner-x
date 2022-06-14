package rs.fon.plannerx.core.account.usecase;


import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;

@UseCase
@RequiredArgsConstructor
public class GetUserUseCase implements GetUser {

    private final rs.fon.plannerx.core.account.ports.out.GetUser getUserService;

    @Override
    public User getUserByEmail(String email) {
        return getUserService.getUserByEmail(email);
    }

    @Override
    public User getUserById(int id) {
        return getUserService.getUserById(id);
    }
}
