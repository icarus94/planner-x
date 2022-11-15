package rs.fon.plannerx.core.account.ports.in.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateUserDto extends SelfValidating<UpdateUserDto> {

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int id;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    String surname;

    @NotNull
    String password;

    @NotNull
    String newPassword;

    @NotNull
    String retypedPassword;

    public UpdateUserDto(int id, String name, String surname, String password, String newPassword, String retypedPassword) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.newPassword = newPassword;
        this.retypedPassword = retypedPassword;
        this.validateSelf();
    }

    @AssertTrue(message = "Passwords are not matching!")
    protected boolean isPasswordMatchedWithRetypedPassword() {
        return this.newPassword.equals(this.retypedPassword);
    }
}
