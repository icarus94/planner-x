package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.ports.in.task.DeleteTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.DeleteTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;

@UseCase
@AllArgsConstructor
public class DeleteTaskUseCase implements DeleteTask {

    private final GetTask getTaskService;
    private final rs.fon.plannerx.core.task.ports.out.task.DeleteTask deleteTaskService;

    @Override
    public void delete(DeleteTaskDto deleteTaskDto) {
        // TODO PERMISSION CHECK
        Task task = this.getTaskService.get(deleteTaskDto.getUserId());
        this.deleteTaskService.delete(task);
    }
}
