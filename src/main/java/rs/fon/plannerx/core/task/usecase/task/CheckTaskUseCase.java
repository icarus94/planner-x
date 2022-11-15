package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.Task;
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

    @Override
    public void check(CheckTaskDto checkTaskDto) {
        Task task = this.getTaskService.get(checkTaskDto.getTaskId());
        System.out.println("IN HERE 1");
        if (!taskListPermissionCheckService.isUpdateAllowed(checkTaskDto.getUserId(), task.getTaskList().getId())) {
            throw new CoreDomainException("Update not allowed!");
        }
        System.out.println("IN HERE 2");
        task.setDone(checkTaskDto.isDone());
        this.saveTaskService.save(task);
    }
}
