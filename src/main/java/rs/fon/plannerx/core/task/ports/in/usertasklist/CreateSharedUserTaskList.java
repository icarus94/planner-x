package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.CreateSharedUserTaskListDto;

public interface CreateSharedUserTaskList {
    void create(CreateSharedUserTaskListDto createSharedUserTaskListDto);
}
