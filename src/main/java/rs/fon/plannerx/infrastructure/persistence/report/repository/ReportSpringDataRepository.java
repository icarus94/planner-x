package rs.fon.plannerx.infrastructure.persistence.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.fon.plannerx.infrastructure.persistence.account.entity.UserJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.report.entity.UserTaskListReportJpaDto;

import java.util.List;

public interface ReportSpringDataRepository extends JpaRepository<UserJpaEntity, Integer> {

    @Query(value = "SELECT new rs.fon.plannerx.infrastructure.persistence.report.entity.UserTaskListReportJpaDto( " +
            " tl.name, " +
            " COUNT(t.id), " +
            " SUM(CASE WHEN (t.done = true) THEN 1 ELSE 0 END), " +
            " SUM(CASE WHEN (t.done = true) THEN 0 ELSE 1 END) )" +
            "FROM TaskJpaEntity t " +
            "LEFT JOIN TaskListJpaEntity tl ON tl.id = t.taskList.id " +
            "LEFT JOIN UserTaskListJpaEntity utl ON tl.id = utl.taskList.id " +
            "WHERE utl.user.id = ?1 AND utl.owner = true " +
            "GROUP BY tl.id")
    List<UserTaskListReportJpaDto> getUserTaskListReports(int userId);
}