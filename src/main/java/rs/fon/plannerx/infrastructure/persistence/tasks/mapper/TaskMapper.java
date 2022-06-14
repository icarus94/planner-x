package rs.fon.plannerx.infrastructure.persistence.tasks.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskListJpaEntity;

@Component
public class TaskMapper {

    TaskListMapper taskListMapper;

    public TaskMapper(@Lazy TaskListMapper taskListMapper) {
        this.taskListMapper = taskListMapper;
    }

    public Task mapToEntity(TaskJpaEntity taskJpaEntity) {
        Task task = new Task(
                taskJpaEntity.getId(),
                taskJpaEntity.getDescription(),
                taskJpaEntity.getDueDate(),
                taskJpaEntity.isDone(),
                taskJpaEntity.getTaskPriority(),
                taskJpaEntity.getDateAdded(),
                taskJpaEntity.getDateModified(),
                null
        );
        task.setTaskList(taskListMapper.mapToEntity(taskJpaEntity.getTaskList(), task));
        return task;
    }

    public TaskJpaEntity mapToJpaEntity(Task task) {
        TaskJpaEntity taskJpaEntity = new TaskJpaEntity(
                task.getId(),
                task.getDescription(),
                task.getDueDate(),
                task.isDone(),
                task.getTaskPriority(),
                task.getDateAdded(),
                task.getDateModified(),
                null
        );
        taskJpaEntity.setTaskList(taskListMapper.mapToJpaEntity(task.getTaskList(), taskJpaEntity));
        return taskJpaEntity;
    }

    Task mapToEntity(TaskJpaEntity taskJpaEntity, TaskList taskList) {
        return new Task(
                taskJpaEntity.getId(),
                taskJpaEntity.getDescription(),
                taskJpaEntity.getDueDate(),
                taskJpaEntity.isDone(),
                taskJpaEntity.getTaskPriority(),
                taskJpaEntity.getDateAdded(),
                taskJpaEntity.getDateModified(),
                taskList
        );
    }

    TaskJpaEntity mapToJpaEntity(Task task, TaskListJpaEntity taskListJpaEntity) {
        return new TaskJpaEntity(
                task.getId(),
                task.getDescription(),
                task.getDueDate(),
                task.isDone(),
                task.getTaskPriority(),
                task.getDateAdded(),
                task.getDateModified(),
                taskListJpaEntity
        );
    }
}
