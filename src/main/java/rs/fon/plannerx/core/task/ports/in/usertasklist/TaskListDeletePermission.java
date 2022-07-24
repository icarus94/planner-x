package rs.fon.plannerx.core.task.ports.in.usertasklist;

public interface TaskListDeletePermission {
    boolean isAllowed(int userId, int taskListId);
}
