package rs.fon.plannerx.core.task.ports.in.usertasklist;

import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.CreateUserTaskListDto;

public interface CreateUserTaskList {
    void create(CreateUserTaskListDto createUserTaskListDto);
}
