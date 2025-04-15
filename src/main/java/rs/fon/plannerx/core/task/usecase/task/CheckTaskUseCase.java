package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.exception.TaskException;
import rs.fon.plannerx.core.task.ports.in.task.CheckTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.CheckTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;
import rs.fon.plannerx.core.task.ports.out.task.SaveTask;
import rs.fon.plannerx.core.task.service.TaskListPermissionCheckInterface;

@UseCase
@AllArgsConstructor
public class CheckTaskUseCase implements CheckTask {
    private final SaveTask saveTaskService;
    private final GetTask getTaskService;
    private final TaskListPermissionCheckInterface taskListPermissionCheckService;
    private final GetUser getUserService;

    @Override
    public void check(CheckTaskDto checkTaskDto) {
        Task task = this.getTaskService.get(checkTaskDto.getTaskId());
        if (!taskListPermissionCheckService.isUpdateAllowed(checkTaskDto.getUserId(), task.getTaskList().getId())) {
            throw TaskException.operationNotAllowed();
        }
        task.setDone(checkTaskDto.isDone());
        task.setDoneBy(getUserService.getById(checkTaskDto.getUserId()));
        this.saveTaskService.save(task);
    }
}
