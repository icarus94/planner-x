package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.ports.in.task.UpdateTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.UpdateTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;
import rs.fon.plannerx.core.task.ports.out.task.SaveTask;

@UseCase
@AllArgsConstructor
public class UpdateTaskUseCase implements UpdateTask {
    private final SaveTask saveTaskService;
    private final GetTask getTaskService;

    @Override
    public void update(UpdateTaskDto updateTaskDto) {
        // TODO PERMISSION CHECK
        Task task = this.getTaskService.get(updateTaskDto.getTaskId());
        task.setDueDate(updateTaskDto.getDueDate());
        task.setTaskPriority(updateTaskDto.getTaskPriority());
        task.setDescription(updateTaskDto.getDescription());
        this.saveTaskService.save(task);
    }
}
