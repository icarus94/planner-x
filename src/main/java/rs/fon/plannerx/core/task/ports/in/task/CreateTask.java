package rs.fon.plannerx.core.task.ports.in.task;

import rs.fon.plannerx.core.task.ports.in.task.dto.CreateTaskDto;

public interface CreateTask {
    void create(CreateTaskDto createTaskDto);
}
