package rs.fon.plannerx.core.task.usecase.tasklist;

import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.exception.TaskException;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.DeleteTaskListDto;
import rs.fon.plannerx.core.task.ports.out.tasklist.GetTaskList;
import rs.fon.plannerx.core.task.ports.out.tasklist.DeleteTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.DeleteUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskLists;
import rs.fon.plannerx.core.task.service.TaskListPermissionCheckInterface;

import java.util.Collection;

@UseCase
@RequiredArgsConstructor
public class DeleteTaskListUseCase implements rs.fon.plannerx.core.task.ports.in.tasklist.DeleteTaskList {

    private final GetUserTaskLists getUserTaskListsService;
    private final DeleteUserTaskList deleteUserTaskListService;
    private final TaskListPermissionCheckInterface taskListPermissionCheckService;
    private final DeleteTaskList deleteTaskListService;
    private final GetTaskList getTaskListService;

    @Override
    public void delete(DeleteTaskListDto deleteTaskListDto) {

        if (!taskListPermissionCheckService.isDeleteTaskListAllowed(deleteTaskListDto.getUserId(), deleteTaskListDto.getTaskListId())) {
            throw TaskException.operationNotAllowed();
        }

        Collection<UserTaskList> userTaskListCollection = getUserTaskListsService.getUserTaskListsByTaskListId(deleteTaskListDto.getTaskListId());


        for (UserTaskList userTaskList : userTaskListCollection) {
            deleteUserTaskListService.delete(userTaskList);
        }

        TaskList taskList = getTaskListService.get(deleteTaskListDto.getTaskListId());
        deleteTaskListService.delete(taskList);
    }
}
