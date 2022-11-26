package rs.fon.plannerx.core.account.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.account.ports.in.dto.UserActiveStatusDto;
import rs.fon.plannerx.core.account.ports.out.UpdateUser;

import static org.mockito.BDDMockito.*;

public class ChangeUserActiveStatusUseCaseTest {

    private GetUser getUserService;
    private UpdateUser updateUserService;

    @BeforeEach
    void init() {
        getUserService = Mockito.mock(GetUser.class);
        updateUserService = Mockito.mock(UpdateUser.class);
    }

    @Test
    public void test() {
        UserActiveStatusDto userActiveStatusDto = new UserActiveStatusDto(1, true);
        ChangeUserActiveStatusUseCase changeUserActiveStatusUseCase = new ChangeUserActiveStatusUseCase(getUserService, updateUserService);
        User userMocked = Mockito.mock(User.class);

        given(getUserService.getById(eq(1))).willReturn(userMocked);
        // act
        changeUserActiveStatusUseCase.change(userActiveStatusDto);

        // assert
        verify(updateUserService, times(1)).update(any(User.class));
        verify(getUserService, times(1)).getById(eq(1));
        verify(userMocked, times(1)).setActive(eq(true));
    }
}
