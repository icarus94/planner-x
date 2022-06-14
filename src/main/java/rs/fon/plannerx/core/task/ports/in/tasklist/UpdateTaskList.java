package rs.fon.plannerx.core.task.ports.in.tasklist;

import rs.fon.plannerx.core.task.ports.in.tasklist.dto.UpdateTaskListDto;

public interface UpdateTaskList {
    void update(UpdateTaskListDto updateTaskListDto);
}
