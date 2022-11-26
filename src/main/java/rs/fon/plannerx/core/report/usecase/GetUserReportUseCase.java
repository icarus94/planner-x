package rs.fon.plannerx.core.report.usecase;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.report.domain.UserTaskListReport;
import rs.fon.plannerx.core.report.ports.in.GetUserReport;
import rs.fon.plannerx.core.report.ports.in.dto.UserDto;

import java.util.Set;

@UseCase
@AllArgsConstructor
public class GetUserReportUseCase implements GetUserReport {
    private final rs.fon.plannerx.core.report.ports.out.GetUserReport getUserReportService;

    @Override
    public Set<UserTaskListReport> getAllTaskListsReport(UserDto userDto) {
        return this.getUserReportService.getAllTaskListsReport(userDto.getId());
    }
}
