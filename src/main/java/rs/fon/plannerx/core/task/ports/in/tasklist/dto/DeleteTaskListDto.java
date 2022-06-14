package rs.fon.plannerx.core.task.ports.in.tasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class DeleteTaskListDto extends SelfValidating<DeleteTaskListDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int taskListId;

    public DeleteTaskListDto(int userId, int taskListId) {
        this.userId = userId;
        this.taskListId = taskListId;
        this.validateSelf();
    }
}
