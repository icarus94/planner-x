package rs.fon.plannerx.core.report.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserTaskListReport {

    private String taskListName;

    private int totalCountTasks;

    private int totalCountDoneTasks;

    private int totalCountUnfinishedTasks;
}
