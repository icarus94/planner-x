package rs.fon.plannerx.core.task.ports.in.task;

import rs.fon.plannerx.core.task.ports.in.task.dto.CheckTaskDto;

public interface CheckTask {
    void check(CheckTaskDto checkTaskDto);
}
