package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.ports.in.task.DeleteTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.DeleteTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;
import rs.fon.plannerx.core.task.service.TaskListPermissionCheckInterface;

@UseCase
@AllArgsConstructor
public class DeleteTaskUseCase implements DeleteTask {

    private final GetTask getTaskService;
    private final rs.fon.plannerx.core.task.ports.out.task.DeleteTask deleteTaskService;
    private final TaskListPermissionCheckInterface taskListPermissionCheckService;

    @Override
    public void delete(DeleteTaskDto deleteTaskDto) {
        Task task = this.getTaskService.get(deleteTaskDto.getTaskId());
        if (!taskListPermissionCheckService.isUpdateAllowed(deleteTaskDto.getUserId(), task.getTaskList().getId())) {
            throw new CoreDomainException("Delete not allowed!");
        }
        this.deleteTaskService.delete(task);
    }
}
