package rs.fon.plannerx.infrastructure.persistence.tasks.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskJpaEntity;

import java.util.List;

public interface TaskSpringDataRepository extends JpaRepository<TaskJpaEntity, Integer> {
    @Query(value = "FROM TaskJpaEntity t WHERE t.taskList.id = :task_id")
    List<TaskJpaEntity> findAllTasks(@Param("task_id") int task_id, Sort sort);

    @Query("select t from TaskJpaEntity t where t.done = true and t.taskList.id = ?1")
    List<TaskJpaEntity> findTasksByDoneTrueAndTaskList_Id(int taskListId, Sort sort);

    List<TaskJpaEntity> findTasksByDoneFalseAndTaskList_Id(int taskListId, Sort sort);
}
