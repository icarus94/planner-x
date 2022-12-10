package rs.fon.plannerx.core.account.ports.in.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserActiveStatusDto extends SelfValidating<UserActiveStatusDto> {

    @NotNull
    @Digits(fraction = 0, integer = 12)
    @Positive
    int id;

    @NotNull
    boolean active;

    public UserActiveStatusDto(int id, boolean active) {
        this.id = id;
        this.active = active;
        this.validateSelf();
    }
}
