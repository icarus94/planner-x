package rs.fon.plannerx.core.task.ports.in.tasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateTaskListDto extends SelfValidating<CreateTaskListDto> {

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userId;

    @NotNull
    @NotBlank
    String name;

    public CreateTaskListDto(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.validateSelf();
    }
}
