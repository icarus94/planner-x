package rs.fon.plannerx.core.task.ports.out.tasklist;

import rs.fon.plannerx.core.task.domain.TaskList;

public interface GetTaskList {
    TaskList get(int id);
}
