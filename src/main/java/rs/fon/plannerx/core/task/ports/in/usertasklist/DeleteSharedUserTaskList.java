package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.DeleteSharedUserTaskListDto;

public interface DeleteSharedUserTaskList {
    void delete(DeleteSharedUserTaskListDto deleteSharedUserTaskListDto);
}
