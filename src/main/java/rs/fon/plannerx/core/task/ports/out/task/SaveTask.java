package rs.fon.plannerx.core.task.ports.out.task;

import rs.fon.plannerx.core.task.domain.Task;

public interface SaveTask {
    void save(Task task);
}
