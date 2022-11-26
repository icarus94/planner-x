package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.RemoveSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.RemoveUserFromSharedTaskListDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.DeleteUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;


@UseCase
@AllArgsConstructor
public class RemoveSharedUserTaskListUseCase implements RemoveSharedUserTaskList {
    private final GetUserTaskList getUserTaskListService;
    private final DeleteUserTaskList deleteUserTaskListService;

    @Override
    public void remove(RemoveUserFromSharedTaskListDto removeUserFromSharedTaskListDto) {
        UserTaskList userTaskList = getUserTaskListService.get(
                removeUserFromSharedTaskListDto.getInvokerUserId(),
                removeUserFromSharedTaskListDto.getTargetTaskListId()
        );
        if (!userTaskList.isOwner()) {
            throw new CoreDomainException("User doesn't own this task list");
        }
        userTaskList = getUserTaskListService.get(
                removeUserFromSharedTaskListDto.getTargetUserId(),
                removeUserFromSharedTaskListDto.getTargetTaskListId()
        );
        deleteUserTaskListService.delete(userTaskList);
    }
}
