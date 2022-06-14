package rs.fon.plannerx.core.account.ports.in.dto;


import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterUserDto extends SelfValidating<RegisterUserDto> {

    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    String surname;

    @NotNull
    @NotBlank
    String email;

    @NotNull
    @NotBlank
    String password;

    @NotNull
    @NotBlank
    String retypedPassword;

    public RegisterUserDto(String name, String surname, String email, String password, String retypedPassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.retypedPassword = retypedPassword;
        this.validateSelf();
    }

    @AssertTrue(message = "Passwords are not matching!")
    protected boolean isPasswordMatchedWithRetypedPassword() {
        return this.password.equals(this.retypedPassword);
    }
}
