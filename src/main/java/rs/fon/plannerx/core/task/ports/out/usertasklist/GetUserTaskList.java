package rs.fon.plannerx.core.task.ports.out.usertasklist;

import rs.fon.plannerx.core.task.domain.UserTaskList;

public interface GetUserTaskList {
    UserTaskList get(int userId, int taskListId);
}
