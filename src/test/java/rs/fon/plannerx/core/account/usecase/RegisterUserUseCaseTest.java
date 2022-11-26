package rs.fon.plannerx.core.account.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.domain.UserRole;
import rs.fon.plannerx.core.account.ports.in.dto.RegisterUserDto;
import rs.fon.plannerx.core.account.ports.out.DoesUserExist;
import rs.fon.plannerx.core.account.ports.out.SaveUser;
import rs.fon.plannerx.core.exception.CoreDomainException;

import static org.mockito.BDDMockito.*;

public class RegisterUserUseCaseTest {

    private SaveUser saveUserService;

    private DoesUserExist doesUserExistService;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        saveUserService = Mockito.mock(SaveUser.class);
        doesUserExistService = Mockito.mock(DoesUserExist.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
    }

    @Test
    public void test_emailDoesNotExist() {
        given(doesUserExistService.doesExistByEmail(eq("test@email.com"))).willReturn(false);
        given(passwordEncoder.encode(eq("pass1"))).willReturn("encodedPass");
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        RegisterUserDto registerUserDto = new RegisterUserDto(
                "name",
                "surname",
                "test@email.com",
                "pass1",
                "pass1"
        );
        RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(saveUserService, doesUserExistService, passwordEncoder);

        // act
        registerUserUseCase.register(registerUserDto);

        // assert
        verify(saveUserService, times(1)).save(argument.capture());
        Assertions.assertTrue(argument.getValue().isActive());
        Assertions.assertEquals(UserRole.ROLE_REGULAR, argument.getValue().getRole());
        Assertions.assertEquals(registerUserDto.getEmail(), argument.getValue().getEmail());
        Assertions.assertEquals("encodedPass", argument.getValue().getPassword());
        Assertions.assertEquals(registerUserDto.getName(), argument.getValue().getName());
        Assertions.assertEquals(registerUserDto.getSurname(), argument.getValue().getSurname());

    }

    @Test()
    public void test_emailDoesExist() {
        RegisterUserDto registerUserDto = new RegisterUserDto(
                "name",
                "surname",
                "test@email.com",
                "pass1",
                "pass1"
        );

        given(doesUserExistService.doesExistByEmail(eq(registerUserDto.getEmail()))).willReturn(true);

        RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(saveUserService, doesUserExistService, passwordEncoder);

        // act
        Assertions.assertThrows(CoreDomainException.class, () -> {
            registerUserUseCase.register(registerUserDto);
        });

        // assert
        verify(saveUserService, times(0)).save(any(User.class));
        verify(doesUserExistService, times(1)).doesExistByEmail(eq(registerUserDto.getEmail()));
        verify(passwordEncoder, times(0)).encode(anyString());
    }
}
