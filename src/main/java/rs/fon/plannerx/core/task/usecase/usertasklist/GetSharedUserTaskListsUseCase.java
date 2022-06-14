package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.GetSharedUserTaskLists;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskLists;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@UseCase
@AllArgsConstructor
public class GetSharedUserTaskListsUseCase implements GetSharedUserTaskLists {

    private final GetUserTaskLists getUserTaskListsService;

    @Override
    public Map<TaskList, Set<UserTaskList>> getShared(int userId) {
        // get usertasklist
        Collection<UserTaskList> userTaskLists = this.getUserTaskListsService.getUserTaskListsWithOwnership(userId);
        // foreach tasklist get usertasklist that are non-owner
        Map<TaskList, Set<UserTaskList>> taskListSetMap = new LinkedHashMap<>();
        for (UserTaskList userTaskList : userTaskLists) {
            taskListSetMap.put(
                    userTaskList.getTaskList(),
                    this.getUserTaskListsService.getUserTaskListsWithoutOwnership(userTaskList.getTaskList().getId())
            );
        }
        return taskListSetMap;
    }
}
