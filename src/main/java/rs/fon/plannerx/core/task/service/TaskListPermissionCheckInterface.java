package rs.fon.plannerx.core.task.service;

public interface TaskListPermissionCheckInterface {

    boolean isUpdateAllowed(int userId, int taskListId);

    boolean isDeleteAllowed(int userId, int taskListId);
}
