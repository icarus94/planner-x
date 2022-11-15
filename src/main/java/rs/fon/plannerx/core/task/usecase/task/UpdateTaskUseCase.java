package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.ports.in.task.UpdateTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.UpdateTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;
import rs.fon.plannerx.core.task.ports.out.task.SaveTask;
import rs.fon.plannerx.core.task.service.TaskListPermissionCheckInterface;

@UseCase
@AllArgsConstructor
public class UpdateTaskUseCase implements UpdateTask {
    private final SaveTask saveTaskService;
    private final GetTask getTaskService;
    private final TaskListPermissionCheckInterface taskListPermissionCheckService;


    @Override
    public void update(UpdateTaskDto updateTaskDto) {
        Task task = this.getTaskService.get(updateTaskDto.getTaskId());

        if (!taskListPermissionCheckService.isUpdateAllowed(updateTaskDto.getUserId(), task.getTaskList().getId())) {
            throw new CoreDomainException("Update not allowed!");
        }

        task.setDueDate(updateTaskDto.getDueDate());
        task.setTaskPriority(updateTaskDto.getTaskPriority());
        task.setDescription(updateTaskDto.getDescription());
        this.saveTaskService.save(task);
    }
}
