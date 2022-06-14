package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.domain.TaskListPermission;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateUserTaskListDto extends SelfValidating<CreateUserTaskListDto> {

    @NotNull
    User user;

    @NotNull
    TaskList taskList;

    @NotNull
    boolean owner;

    @NotNull
    TaskListPermission taskListPermission;

    public CreateUserTaskListDto(User user, TaskList taskList, boolean owner, TaskListPermission taskListPermission) {
        this.user = user;
        this.taskList = taskList;
        this.owner = owner;
        this.taskListPermission = taskListPermission;
        this.validateSelf();
    }
}
