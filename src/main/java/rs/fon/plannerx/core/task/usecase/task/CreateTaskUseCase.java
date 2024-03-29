package rs.fon.plannerx.core.task.usecase.task;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.exception.TaskException;
import rs.fon.plannerx.core.task.ports.in.task.CreateTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.CreateTaskDto;
import rs.fon.plannerx.core.task.ports.out.task.SaveTask;
import rs.fon.plannerx.core.task.ports.out.tasklist.GetTaskList;
import rs.fon.plannerx.core.task.service.TaskListPermissionCheckInterface;

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
public class CreateTaskUseCase implements CreateTask {
    private final SaveTask saveTaskService;
    private final GetTaskList getTaskListService;
    private final TaskListPermissionCheckInterface taskListPermissionCheckService;


    @Override
    public void create(CreateTaskDto createTaskDto) {
        if (!taskListPermissionCheckService.isUpdateAllowed(createTaskDto.getUserId(), createTaskDto.getTaskListId())) {
            throw TaskException.operationNotAllowed();
        }

        Task task = new Task();
        task.setTaskList(getTaskListService.get(createTaskDto.getTaskListId()));
        task.setTaskPriority(createTaskDto.getTaskPriority());
        task.setDescription(createTaskDto.getDescription());
        task.setDueDate(createTaskDto.getDueDate());
        task.setDone(false);
        LocalDateTime localDateTime = LocalDateTime.now();
        task.setDateAdded(localDateTime);
        task.setDateModified(localDateTime);

        this.saveTaskService.save(task);
    }
}
