package rs.fon.plannerx.core.task.ports.in.task;

import rs.fon.plannerx.core.task.ports.in.task.dto.DeleteTaskDto;

public interface DeleteTask {
    void delete(DeleteTaskDto deleteTaskDto);
}
