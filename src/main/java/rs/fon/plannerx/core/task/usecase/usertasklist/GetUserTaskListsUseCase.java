package rs.fon.plannerx.core.task.usecase.usertasklist;


import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.GetUserTaskLists;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UserTaskListFilterDto;

import java.util.Collection;

@UseCase
@AllArgsConstructor
public class GetUserTaskListsUseCase implements GetUserTaskLists {

    private final rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskLists getUserTaskListsService;

    @Override
    public Collection<UserTaskList> getUserTaskLists(int userId, UserTaskListFilterDto userTaskListFilterDto) {
        return this.getUserTaskListsService.getUserTaskLists(userId, userTaskListFilterDto);
    }
}
