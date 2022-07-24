package rs.fon.plannerx.core.task.ports.in.usertasklist;

public interface TaskListUpdatePermission {
    boolean isAllowed(int userId, int taskListId);
}
