package rs.fon.plannerx.core.task.ports.out.task;

import rs.fon.plannerx.core.task.domain.Task;

public interface DeleteTask {
    void delete(Task task);
}
