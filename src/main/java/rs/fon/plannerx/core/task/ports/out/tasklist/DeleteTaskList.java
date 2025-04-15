package rs.fon.plannerx.core.task.ports.out.tasklist;

import rs.fon.plannerx.core.task.domain.TaskList;

public interface DeleteTaskList {
    void delete(TaskList taskList);
}
