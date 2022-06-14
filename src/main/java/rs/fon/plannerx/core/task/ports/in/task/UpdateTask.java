package rs.fon.plannerx.core.task.ports.in.task;

import rs.fon.plannerx.core.task.ports.in.task.dto.UpdateTaskDto;

public interface UpdateTask {
    void update(UpdateTaskDto updateTaskDto);
}
