package rs.fon.plannerx.core.task.ports.out.usertasklist;

import rs.fon.plannerx.core.task.domain.UserTaskList;

public interface SaveUserTaskList {
    void save(UserTaskList userTaskList);
}
