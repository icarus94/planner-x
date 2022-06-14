package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UserTaskListFilterDto;

import java.util.Collection;

public interface GetUserTaskLists {
    Collection<UserTaskList> getUserTaskLists(int userId, UserTaskListFilterDto userTaskListFilterDto);
}
