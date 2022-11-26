package rs.fon.plannerx.core.report.ports.in;

import rs.fon.plannerx.core.report.domain.UserTaskListReport;
import rs.fon.plannerx.core.report.ports.in.dto.UserDto;

import java.util.Set;

public interface GetUserReport {
    Set<UserTaskListReport> getAllTaskListsReport(UserDto userDto);
}
