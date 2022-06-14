package rs.fon.plannerx.infrastructure.persistence.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskListJpaEntity;

public interface TaskListSpringDataRepository extends JpaRepository<TaskListJpaEntity, Integer> {
}
