package rs.fon.plannerx.core.task.ports.in.tasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateTaskListDto extends SelfValidating<UpdateTaskListDto> {

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int taskListId;

    @NotNull
    @NotBlank
    String name;

    public UpdateTaskListDto(int userId, int taskListId, String name) {
        this.userId = userId;
        this.taskListId = taskListId;
        this.name = name;
        this.validateSelf();
    }
}
