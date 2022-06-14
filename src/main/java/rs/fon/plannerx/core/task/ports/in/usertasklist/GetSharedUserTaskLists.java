package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.domain.UserTaskList;

import java.util.Map;
import java.util.Set;

public interface GetSharedUserTaskLists {
    Map<TaskList, Set<UserTaskList>> getShared(int userId);
}
