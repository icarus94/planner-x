package rs.fon.plannerx.infrastructure.persistence.report.mapper;

import org.springframework.stereotype.Component;
import rs.fon.plannerx.core.report.domain.UserTaskListReport;
import rs.fon.plannerx.infrastructure.persistence.report.entity.UserTaskListReportJpaDto;

@Component
public class UserTaskListReportMapper {

    public UserTaskListReport mapToEntity(UserTaskListReportJpaDto userTaskListReportJpaDto) {
        return new UserTaskListReport(
                userTaskListReportJpaDto.getTaskListName(),
                (int) userTaskListReportJpaDto.getTotalCountTasks(),
                (int) userTaskListReportJpaDto.getTotalCountDoneTasks(),
                (int) userTaskListReportJpaDto.getTotalCountUnfinishedTasks()
        );
    }

    public UserTaskListReportJpaDto mapToJpaDto(UserTaskListReport userTaskListReport) {
        return new UserTaskListReportJpaDto(
                userTaskListReport.getTaskListName(),
                userTaskListReport.getTotalCountTasks(),
                userTaskListReport.getTotalCountDoneTasks(),
                userTaskListReport.getTotalCountUnfinishedTasks()
        );
    }
}
