package rs.fon.plannerx.core.task.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TaskPriority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);
    private final int priority;
}
