package rs.fon.plannerx.core.report.usecase;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.report.domain.UserTaskListReport;
import rs.fon.plannerx.core.report.ports.in.GetUserAllTaskListReport;

import java.util.Set;

@UseCase
@AllArgsConstructor
public class GetUserAllTaskListReportUseCase implements GetUserAllTaskListReport {
    private final rs.fon.plannerx.core.report.ports.out.GetUserAllTaskListReport getUserAllTaskListReportService;

    @Override
    public Set<UserTaskListReport> get(int userId) {
        return this.getUserAllTaskListReportService.get(userId);
    }
}
