package rs.fon.plannerx.core.report.ports.in.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserDto extends SelfValidating<UserDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int id;

    public UserDto(int id) {
        this.id = id;
        this.validateSelf();
    }
}
