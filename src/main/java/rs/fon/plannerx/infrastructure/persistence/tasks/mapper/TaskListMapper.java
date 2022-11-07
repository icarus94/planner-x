package rs.fon.plannerx.infrastructure.persistence.tasks.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskListJpaEntity;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TaskListMapper {

    TaskMapper taskMapper;

    public TaskList mapToEntity(TaskListJpaEntity taskListJpaEntity) {
        TaskList taskList = new TaskList(
                taskListJpaEntity.getId(),
                taskListJpaEntity.getName(),
                new LinkedHashSet<>()
        );
        Set<Task> taskSet = taskListJpaEntity.getTasks().stream()
                .map(task -> taskMapper.mapToEntity(task, taskList))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        taskList.setTasks(taskSet);

        return taskList;
    }

    public TaskListJpaEntity mapToJpaEntity(TaskList taskList) {

        TaskListJpaEntity taskListJpaEntity = new TaskListJpaEntity(
                taskList.getId(),
                taskList.getName(),
                new LinkedHashSet<>()
        );

        Set<TaskJpaEntity> taskJpaEntitySet = taskList.getTasks().stream()
                .map(task -> taskMapper.mapToJpaEntity(task, taskListJpaEntity))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        taskListJpaEntity.setTasks(taskJpaEntitySet);
        return taskListJpaEntity;
    }

    TaskList mapToEntity(TaskListJpaEntity taskListJpaEntity, Task taskRef) {
        TaskList taskList = new TaskList(
                taskListJpaEntity.getId(),
                taskListJpaEntity.getName(),
                new LinkedHashSet<>()
        );
        Set<Task> taskSet = taskListJpaEntity.getTasks().stream()
                .map(task -> {
                    if (task.getId() == taskRef.getId()) {
                        return taskRef;
                    }
                    return taskMapper.mapToEntity(task, taskList);
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));

        taskList.setTasks(taskSet);

        return taskList;
    }

    TaskListJpaEntity mapToJpaEntity(TaskList taskList, TaskJpaEntity taskRef) {

        TaskListJpaEntity taskListJpaEntity = new TaskListJpaEntity(
                taskList.getId(),
                taskList.getName(),
                new LinkedHashSet<>()
        );

        Set<TaskJpaEntity> taskJpaEntitySet = taskList.getTasks().stream()
                .map(task -> {
                    if (task.getId() == taskRef.getId()) {
                        return taskRef;
                    }
                    return taskMapper.mapToJpaEntity(task, taskListJpaEntity);
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));

        taskListJpaEntity.setTasks(taskJpaEntitySet);
        return taskListJpaEntity;
    }
}
