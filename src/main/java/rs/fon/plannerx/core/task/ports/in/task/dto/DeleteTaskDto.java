package rs.fon.plannerx.core.task.ports.in.task.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class DeleteTaskDto extends SelfValidating<CreateTaskDto> {

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int taskId;

    public DeleteTaskDto(int userId, int taskId) {
        this.userId = userId;
        this.taskId = taskId;
        this.validateSelf();
    }
}
