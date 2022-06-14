package rs.fon.plannerx.core.task.ports.out.tasklist;

import rs.fon.plannerx.core.task.domain.TaskList;

public interface SaveTaskList {
    TaskList saveAndReturn(TaskList taskList);

    void save(TaskList taskList);
}
