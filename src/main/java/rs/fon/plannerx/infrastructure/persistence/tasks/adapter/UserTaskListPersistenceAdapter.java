package rs.fon.plannerx.infrastructure.persistence.tasks.adapter;

import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.PersistenceAdapter;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UserTaskListFilterDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.DeleteUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskLists;
import rs.fon.plannerx.core.task.ports.out.usertasklist.SaveUserTaskList;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.UserTaskListJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.mapper.UserTaskListMapper;
import rs.fon.plannerx.infrastructure.persistence.tasks.repository.UserTaskListSpringDataRepository;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserTaskListPersistenceAdapter implements GetUserTaskLists, SaveUserTaskList, GetUserTaskList, DeleteUserTaskList {

    private final UserTaskListSpringDataRepository userTaskListSpringDataRepository;

    private final UserTaskListMapper userTaskListMapper;

    private final TaskPersistenceAdapter taskPersistenceAdapter;

    @Override
    public Set<UserTaskList> getUserTaskLists(int userId) {
        return userTaskListSpringDataRepository.getAllByUserIdOrderByTaskListAsc(userId).stream()
                .map(userTaskListMapper::mapToEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<UserTaskList> getUserTaskLists(int userId, UserTaskListFilterDto userTaskListFilterDto) {
        Collection<UserTaskListJpaEntity> userTaskListJpaEntities =
                userTaskListSpringDataRepository.getAllByUserIdOrderByTaskListAsc(userId);

        userTaskListJpaEntities = userTaskListJpaEntities.stream()
                .map(userTaskListJpaEntity -> {
                    userTaskListJpaEntity.getTaskList().setTasks(
                            new LinkedHashSet<>(
                                    this.taskPersistenceAdapter.getByFilter(
                                            userTaskListFilterDto,
                                            userTaskListJpaEntity.getTaskList().getId()
                                    )));
                    return userTaskListJpaEntity;
                })
                .collect(Collectors.toList());

        return userTaskListJpaEntities.stream()
                .map(userTaskListMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<UserTaskList> getUserTaskListsWithOwnership(int userId) {
        return userTaskListSpringDataRepository.getUserTaskListsWithOwnership(userId)
                .stream()
                .map(userTaskListMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Set<UserTaskList> getUserTaskListsWithoutOwnership(int taskListId) {
        return userTaskListSpringDataRepository.getUserTaskListsWithoutOwnership(taskListId)
                .stream()
                .map(userTaskListMapper::mapToEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(UserTaskList userTaskList) {
        userTaskListSpringDataRepository.save(userTaskListMapper.mapToJpaEntity(userTaskList));
    }

    @Override
    public UserTaskList get(int userId, int taskListId) {
        return this.userTaskListMapper.mapToEntity(
                this.userTaskListSpringDataRepository.findByUserIdAndTaskListId(userId, taskListId)
        );
    }

    @Override
    public void remove(UserTaskList userTaskList) {
        this.userTaskListSpringDataRepository.delete(
                this.userTaskListMapper.mapToJpaEntity(
                        userTaskList
                )
        );
    }
}
