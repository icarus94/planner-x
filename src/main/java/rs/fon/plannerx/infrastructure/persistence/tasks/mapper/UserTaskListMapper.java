package rs.fon.plannerx.infrastructure.persistence.tasks.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.infrastructure.persistence.account.mapper.UserMapper;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.UserTaskListJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.UserTaskListJpaEntityPk;

@Component
@AllArgsConstructor
public class UserTaskListMapper {

    UserMapper userMapper;
    TaskListMapper taskListMapper;

    public UserTaskList mapToEntity(UserTaskListJpaEntity userTaskListJpaEntity) {
        return new UserTaskList(
                userMapper.mapToEntity(userTaskListJpaEntity.getUser()),
                taskListMapper.mapToEntity(userTaskListJpaEntity.getTaskList()),
                userTaskListJpaEntity.getTaskListPermission(),
                userTaskListJpaEntity.isOwner(),
                userTaskListJpaEntity.getDateCreated(),
                userTaskListJpaEntity.getDateModified()
        );
    }

    public UserTaskListJpaEntity mapToJpaEntity(UserTaskList userTaskList) {
        UserTaskListJpaEntityPk userTaskListJpaEntityPk = new UserTaskListJpaEntityPk(
                userTaskList.getUser().getId(),
                userTaskList.getTaskList().getId()
        );
        return new UserTaskListJpaEntity(
                userTaskListJpaEntityPk,
                userMapper.mapToJpaEntity(userTaskList.getUser()),
                taskListMapper.mapToJpaEntity(userTaskList.getTaskList()),
                userTaskList.getPermission(),
                userTaskList.isOwner(),
                userTaskList.getDateCreated(),
                userTaskList.getDateModified()
        );
    }
}
