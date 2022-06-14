package rs.fon.plannerx.infrastructure.persistence.tasks.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.UserTaskListJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.UserTaskListJpaEntityPk;

import java.util.Collection;
import java.util.Set;

public interface UserTaskListSpringDataRepository extends JpaRepository<UserTaskListJpaEntity, UserTaskListJpaEntityPk> {
    @Query("FROM UserTaskListJpaEntity ut JOIN FETCH ut.taskList WHERE ut.user.id = ?1 ORDER BY ut.taskList.id")
    Set<UserTaskListJpaEntity> getAllByUserIdOrderByTaskListAsc(int userId);

    @Query("FROM UserTaskListJpaEntity WHERE user.id = ?1 AND taskList.id = ?2")
    UserTaskListJpaEntity findByUserIdAndTaskListId(int userId, int taskId);

    @Query("FROM UserTaskListJpaEntity ut " +
            "JOIN FETCH ut.taskList " +
            "JOIN FETCH ut.taskList.tasks t " +
            "WHERE ut.user.id = ?1 AND (t.done = true AND true=?2) " +
            "ORDER BY ut.taskList.id, t.dateAdded")
    Set<UserTaskListJpaEntity> getByUserIdFiltered(int userId, boolean finished);

    @Query("select u " +
            "from UserTaskListJpaEntity u " +
            "left join u.taskList.tasks tasks " +
            "where u.userTaskListJpaEntityPk.userId = :userId and tasks.done in :dones " +
            "order by u.userTaskListJpaEntityPk.taskListId")
    Set<UserTaskListJpaEntity> getcustom(@Param("userId") int userId, @Param("dones") Collection<Boolean> dones, Sort sort);


    @Query("FROM UserTaskListJpaEntity WHERE user.id = ?1 AND owner = true")
    Collection<UserTaskListJpaEntity> getUserTaskListsWithOwnership(int userId);

    @Query("FROM UserTaskListJpaEntity WHERE taskList.id = ?1 AND owner = false")
    Collection<UserTaskListJpaEntity> getUserTaskListsWithoutOwnership(int taskListId);
}
