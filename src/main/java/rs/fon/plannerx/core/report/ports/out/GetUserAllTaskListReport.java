package rs.fon.plannerx.core.report.ports.out;

import rs.fon.plannerx.core.report.domain.UserTaskListReport;

import java.util.Set;

public interface GetUserAllTaskListReport {
    Set<UserTaskListReport> get(int userId);
}
