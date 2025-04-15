package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.exception.TaskException;
import rs.fon.plannerx.core.task.ports.in.usertasklist.DeleteSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.DeleteSharedUserTaskListDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.DeleteUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;


@UseCase
@AllArgsConstructor
public class DeleteSharedUserTaskListUseCase implements DeleteSharedUserTaskList {
    private final GetUserTaskList getUserTaskListService;
    private final DeleteUserTaskList deleteUserTaskListService;

    @Override
    public void delete(DeleteSharedUserTaskListDto deleteSharedUserTaskListDto) {
        UserTaskList userTaskList = getUserTaskListService.get(
                deleteSharedUserTaskListDto.getInvokerUserId(),
                deleteSharedUserTaskListDto.getTargetTaskListId()
        );
        if (!userTaskList.isOwner()) {
            throw TaskException.operationNotAllowed();
        }
        userTaskList = getUserTaskListService.get(
                deleteSharedUserTaskListDto.getTargetUserId(),
                deleteSharedUserTaskListDto.getTargetTaskListId()
        );
        deleteUserTaskListService.delete(userTaskList);
    }
}
