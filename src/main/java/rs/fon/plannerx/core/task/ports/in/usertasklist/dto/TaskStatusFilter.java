package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatusFilter {
    ALL("All"),
    FINISHED_TASKS("Done tasks"),
    UNFINISHED_TASKS("Ongoing tasks");

    public static final String DEFAULT_STATE = "ALL";
    private final String name;
}
