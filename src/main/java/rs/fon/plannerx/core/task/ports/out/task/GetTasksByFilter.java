package rs.fon.plannerx.core.task.ports.out.task;

import rs.fon.plannerx.core.task.domain.Task;

import java.util.Set;

public interface GetTasksByFilter {
    Set<Task> getByFilter();
}
