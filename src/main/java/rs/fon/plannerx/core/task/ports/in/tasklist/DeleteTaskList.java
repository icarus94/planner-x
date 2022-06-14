package rs.fon.plannerx.core.task.ports.in.tasklist;

import rs.fon.plannerx.core.task.ports.in.tasklist.dto.DeleteTaskListDto;

public interface DeleteTaskList {
    void delete(DeleteTaskListDto deleteTaskListDto);
}
