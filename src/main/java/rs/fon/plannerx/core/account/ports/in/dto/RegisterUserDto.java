package rs.fon.plannerx.core.account.ports.in.dto;


import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterUserDto extends SelfValidating<RegisterUserDto> {

    @NotNull(message = "register.user.name")
    @NotBlank(message = "register.user.name")
    String name;

    @NotNull(message = "register.user.surname")
    @NotBlank(message = "register.user.surname")
    String surname;

    @NotNull(message = "register.user.email")
    @NotBlank(message = "register.user.email")
    @Email(message = "register.user.email.format")
    String email;

    @NotNull(message = "register.user.password")
    @NotBlank(message = "register.user.password")
    String password;

    @NotNull(message = "register.user.retyped.password")
    @NotBlank(message = "register.user.retyped.password")
    String retypedPassword;

    public RegisterUserDto(String name, String surname, String email, String password, String retypedPassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.retypedPassword = retypedPassword;
        this.validateSelf();
    }

    @AssertTrue(message = "register.user.retyped.password.check")
    protected boolean isPasswordMatchedWithRetypedPassword() {
        return this.password.equals(this.retypedPassword);
    }
}
