package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UpdateSharedUserTaskListDto;

public interface UpdateSharedUserTaskList {
    void update(UpdateSharedUserTaskListDto updateSharedUserTaskListDto);
}
