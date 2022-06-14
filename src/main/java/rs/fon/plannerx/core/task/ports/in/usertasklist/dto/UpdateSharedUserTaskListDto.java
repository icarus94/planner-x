package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;
import rs.fon.plannerx.core.task.domain.TaskListPermission;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateSharedUserTaskListDto extends SelfValidating<UpdateSharedUserTaskListDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int invokerUserId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int targetUserId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int targetTaskListId;

    @NotNull
    TaskListPermission taskListPermission;

    public UpdateSharedUserTaskListDto(int invokerUserId, int targetUserId, int targetTaskListId, TaskListPermission taskListPermission) {
        this.invokerUserId = invokerUserId;
        this.targetUserId = targetUserId;
        this.targetTaskListId = targetTaskListId;
        this.taskListPermission = taskListPermission;
        this.validateSelf();
    }
}
