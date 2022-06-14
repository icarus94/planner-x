package rs.fon.plannerx.core.task.ports.in.tasklist;

import rs.fon.plannerx.core.task.ports.in.tasklist.dto.CreateTaskListDto;

public interface CreateTaskList {
    void create(CreateTaskListDto createTaskListDto);
}
