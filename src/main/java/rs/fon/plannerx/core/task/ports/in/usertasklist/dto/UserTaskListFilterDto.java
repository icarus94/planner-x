package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import rs.fon.plannerx.common.SelfValidating;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserTaskListFilterDto extends SelfValidating<UserTaskListFilterDto> {

    @NotNull
    TaskOrderByFilter taskOrderByFilter;

    @NotNull
    TaskStatusFilter taskStatusFilter;

    public UserTaskListFilterDto(TaskOrderByFilter taskOrderByFilter, TaskStatusFilter taskStatusFilter) {
        this.taskOrderByFilter = taskOrderByFilter;
        this.taskStatusFilter = taskStatusFilter;
        this.validateSelf();
    }
}
