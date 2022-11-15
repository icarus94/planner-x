package rs.fon.plannerx.core.task.usecase.tasklist;

import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.UpdateTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.UpdateTaskListDto;
import rs.fon.plannerx.core.task.ports.out.tasklist.GetTaskList;
import rs.fon.plannerx.core.task.ports.out.tasklist.SaveTaskList;
import rs.fon.plannerx.core.task.service.TaskListPermissionCheckInterface;

@UseCase
@RequiredArgsConstructor
public class UpdateTaskListUseCase implements UpdateTaskList {
    private final GetTaskList getTaskListService;
    private final SaveTaskList saveTaskListService;
    private final TaskListPermissionCheckInterface taskListPermissionCheckService;

    @Override
    public void update(UpdateTaskListDto updateTaskListDto) {
        if (!taskListPermissionCheckService.isUpdateAllowed(updateTaskListDto.getUserId(), updateTaskListDto.getTaskListId())) {
            throw new CoreDomainException("You don't have task list permission");
        }
        TaskList taskList = this.getTaskListService.get(updateTaskListDto.getTaskListId());
        taskList.setName(updateTaskListDto.getName());
        this.saveTaskListService.save(taskList);
    }
}
