package rs.fon.plannerx.core.task.ports.out.usertasklist;

import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UserTaskListFilterDto;

import java.util.Collection;
import java.util.Set;

public interface GetUserTaskLists {
    Set<UserTaskList> getUserTaskLists(int userId);

    Collection<UserTaskList> getUserTaskLists(int userId, UserTaskListFilterDto userTaskListFilterDto);

    Collection<UserTaskList> getUserTaskListsWithOwnership(int userId);

    Set<UserTaskList> getUserTaskListsWithoutOwnership(int taskListId);

    Collection<UserTaskList> getUserTaskListsByTaskListId(int taskListId);
}
