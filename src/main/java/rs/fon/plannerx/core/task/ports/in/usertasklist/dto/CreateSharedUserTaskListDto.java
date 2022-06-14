package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;
import rs.fon.plannerx.core.task.domain.TaskListPermission;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateSharedUserTaskListDto extends SelfValidating<CreateUserTaskListDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int invokerUserId;

    @NotNull
    @NotBlank
    String targetEmail;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int targetTaskListId;

    @NotNull
    TaskListPermission taskListPermission;

    public CreateSharedUserTaskListDto(int invokerUserId, String targetEmail, int targetTaskListId, TaskListPermission taskListPermission) {
        this.invokerUserId = invokerUserId;
        this.targetEmail = targetEmail;
        this.targetTaskListId = targetTaskListId;
        this.taskListPermission = taskListPermission;
        this.validateSelf();
    }
}
