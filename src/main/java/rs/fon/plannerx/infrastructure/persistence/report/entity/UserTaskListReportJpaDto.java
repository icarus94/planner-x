package rs.fon.plannerx.infrastructure.persistence.report.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskListReportJpaDto {
    private String taskListName;

    private long totalCountTasks;

    private long totalCountDoneTasks;

    private long totalCountUnfinishedTasks;
}
