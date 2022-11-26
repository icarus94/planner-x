package rs.fon.plannerx.infrastructure.persistence.report.adapter;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.PersistenceAdapter;
import rs.fon.plannerx.core.report.domain.UserTaskListReport;
import rs.fon.plannerx.core.report.ports.out.GetUserReport;
import rs.fon.plannerx.infrastructure.persistence.report.mapper.UserTaskListReportMapper;
import rs.fon.plannerx.infrastructure.persistence.report.repository.ReportSpringDataRepository;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@PersistenceAdapter
public class ReportPersistenceAdapter implements GetUserReport {
    private final ReportSpringDataRepository reportSpringDataRepository;
    private final UserTaskListReportMapper userTaskListReportMapper;

    @Override
    public Set<UserTaskListReport> getAllTaskListsReport(int userId) {
        return reportSpringDataRepository.getUserTaskListReports(userId).stream()
                .map(userTaskListReportMapper::mapToEntity)
                .collect(Collectors.toSet());
    }
}
