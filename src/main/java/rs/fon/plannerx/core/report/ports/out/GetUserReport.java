package rs.fon.plannerx.core.report.ports.out;

import rs.fon.plannerx.core.report.domain.UserTaskListReport;

import java.util.Set;

public interface GetUserReport {
    Set<UserTaskListReport> getAllTaskListsReport(int userId);
}
