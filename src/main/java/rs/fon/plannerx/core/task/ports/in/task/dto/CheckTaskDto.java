package rs.fon.plannerx.core.task.ports.in.task.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CheckTaskDto extends SelfValidating<CheckTaskDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int taskId;

    @NotNull
    boolean done;

    public CheckTaskDto(int userId, int taskId, boolean done) {
        this.userId = userId;
        this.taskId = taskId;
        this.done = done;
        this.validateSelf();
    }
}
