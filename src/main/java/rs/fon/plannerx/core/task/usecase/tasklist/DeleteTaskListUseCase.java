package rs.fon.plannerx.core.task.usecase.tasklist;

import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.TaskListPermission;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.DeleteTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.DeleteTaskListDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;

@UseCase
@RequiredArgsConstructor
public class DeleteTaskListUseCase implements DeleteTaskList {
    //    private final DoesTaskListExist doesTaskListExistService;
    private final GetUserTaskList getUserTaskListService;

    @Override
    public void delete(DeleteTaskListDto deleteTaskListDto) {
//        if (!doesTaskListExistService.check(deleteTaskListDto.getTaskListId())) {
//            throw new CoreDomainException("Task list doesn't exist");
//        }
        // TODO PERMISSION CHECK
        UserTaskList userTaskList = getUserTaskListService.get(
                deleteTaskListDto.getUserId(),
                deleteTaskListDto.getTaskListId()
        );
        if (userTaskList.getPermission() != TaskListPermission.ALL) {
            throw new CoreDomainException("You don't have task list permission");
        }

        // remove all usertasklist

        // remove tasklist  & all tasks
    }
}
