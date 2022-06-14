package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.RemoveUserFromSharedTaskListDto;

public interface RemoveSharedUserTaskList {
    void remove(RemoveUserFromSharedTaskListDto removeUserFromSharedTaskListDto);
}
