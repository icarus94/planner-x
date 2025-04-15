package rs.fon.plannerx.core.task.service;

public interface TaskListPermissionCheckInterface {

    boolean isUpdateAllowed(int userId, int taskListId);

    boolean isDeleteTaskAllowed(int userId, int taskListId);

    boolean isDeleteTaskListAllowed(int userId, int taskListId);
}
