package rs.fon.plannerx.core.account.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.out.GetUser;

import static org.mockito.BDDMockito.*;

public class GetUserUseCaseTest {
    private GetUser getUserService;

    @BeforeEach
    void init() {
        getUserService = Mockito.mock(GetUser.class);
    }

    @Test
    public void test_getByEmail() {
        GetUserUseCase getUserUseCase = new GetUserUseCase(getUserService);
        String email = "test@email.com";

        User userMock = Mockito.mock(User.class);
        given(getUserService.getByEmail(eq(email))).willReturn(userMock);

        // act
        User result = getUserUseCase.getByEmail(email);

        // assert
        verify(getUserService, times(1)).getByEmail(anyString());
        Assertions.assertEquals(userMock, result);
    }

    @Test
    public void test_getById() {
        GetUserUseCase getUserUseCase = new GetUserUseCase(getUserService);
        int userId = 2;
        User userMock = Mockito.mock(User.class);
        given(getUserService.getById(eq(userId))).willReturn(userMock);

        // act
        User result = getUserUseCase.getById(userId);

        // assert
        verify(getUserService, times(1)).getById(anyInt());
        Assertions.assertEquals(userMock, result);
    }
}
