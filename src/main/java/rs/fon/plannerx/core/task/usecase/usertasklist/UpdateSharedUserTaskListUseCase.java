package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.exception.TaskException;
import rs.fon.plannerx.core.task.ports.in.usertasklist.UpdateSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UpdateSharedUserTaskListDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.SaveUserTaskList;

@UseCase
@AllArgsConstructor
public class UpdateSharedUserTaskListUseCase implements UpdateSharedUserTaskList {
    private final GetUserTaskList getUserTaskListService;
    private final SaveUserTaskList saveUserTaskListService;

    @Override
    public void update(UpdateSharedUserTaskListDto updateSharedUserTaskListDto) {
        UserTaskList userTaskList = getUserTaskListService.get(
                updateSharedUserTaskListDto.getInvokerUserId(),
                updateSharedUserTaskListDto.getTargetTaskListId()
        );
        if (!userTaskList.isOwner()) {
            throw TaskException.operationNotAllowed();
        }
        userTaskList = getUserTaskListService.get(
                updateSharedUserTaskListDto.getTargetUserId(),
                updateSharedUserTaskListDto.getTargetTaskListId()
        );
        userTaskList.setPermission(updateSharedUserTaskListDto.getTaskListPermission());
        saveUserTaskListService.save(userTaskList);
    }
}
