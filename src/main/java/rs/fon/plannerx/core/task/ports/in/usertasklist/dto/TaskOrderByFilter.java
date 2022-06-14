package rs.fon.plannerx.core.task.ports.in.usertasklist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskOrderByFilter {
    DEFAULT("Default"),
    DATE_ASC("Date Ascending"),
    DATE_DESC("Date Descending"),
    PRIORITY_ASC("Priority Ascending"),
    PRIORITY_DESC("Priority Descending");

    public static final String DEFAULT_STATE = "DEFAULT";
    private final String orderBy;
}
