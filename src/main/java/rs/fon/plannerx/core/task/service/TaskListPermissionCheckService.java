package rs.fon.plannerx.core.task.service;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.MyService;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.TaskListPermission;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;


@MyService
@AllArgsConstructor
public class TaskListPermissionCheckService implements TaskListPermissionCheckInterface {

    private final GetUserTaskList getUserTaskListService;


    @Override
    public boolean isUpdateAllowed(int userId, int taskListId) {
        UserTaskList userTaskList = this.getUserTaskList(userId, taskListId);
        TaskListPermission taskListPermission = userTaskList.getPermission();
        System.out.println("IN HERE RESULT [[ " + taskListPermission.name());

        return taskListPermission.equals(TaskListPermission.READ_UPDATE) ||
                taskListPermission.equals(TaskListPermission.ALL);
    }

    @Override
    public boolean isDeleteAllowed(int userId, int taskListId) {
        UserTaskList userTaskList = this.getUserTaskList(userId, taskListId);
        TaskListPermission taskListPermission = userTaskList.getPermission();
        return taskListPermission.equals(TaskListPermission.ALL);
    }

    private UserTaskList getUserTaskList(int userId, int taskListId) {
        UserTaskList userTaskList = this.getUserTaskListService.get(userId, taskListId);
        if (userTaskList == null) {
            throw new CoreDomainException("User doesn't own this task list");
        }
        return userTaskList;
    }
}
