package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.TaskListPermission;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.TaskListDeletePermission;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;

@UseCase
@AllArgsConstructor
public class TaskListDeletePermissionUseCase implements TaskListDeletePermission {
    private final GetUserTaskList getUserTaskListService;

    @Override
    public boolean isAllowed(int userId, int taskListId) {
        UserTaskList userTaskList = getUserTaskListService.get(
                userId,
                taskListId
        );
        if (userTaskList == null) {
            return false;
        }
        return userTaskList.getPermission().equals(TaskListPermission.ALL);
    }
}
