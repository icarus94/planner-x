package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.ports.in.task.CheckTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.CheckTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;
import rs.fon.plannerx.core.task.ports.out.task.SaveTask;

@UseCase
@AllArgsConstructor
public class CheckTaskUseCase implements CheckTask {
    private final SaveTask saveTaskService;
    private final GetTask getTaskService;

    @Override
    public void check(CheckTaskDto checkTaskDto) {
        // TODO PERMISSION CHECK
        Task task = this.getTaskService.get(checkTaskDto.getTaskId());
        task.setDone(checkTaskDto.isDone());
        this.saveTaskService.save(task);
    }
}
