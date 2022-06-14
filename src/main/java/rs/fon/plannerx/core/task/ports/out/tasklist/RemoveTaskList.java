package rs.fon.plannerx.core.task.ports.out.tasklist;

import rs.fon.plannerx.core.task.domain.TaskList;

public interface RemoveTaskList {
    void remove(TaskList taskList);
}
