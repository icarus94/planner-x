package rs.fon.plannerx.infrastructure.persistence.tasks.adapter;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import rs.fon.plannerx.common.PersistenceAdapter;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.ports.out.tasklist.DeleteTaskList;
import rs.fon.plannerx.core.task.ports.out.tasklist.GetTaskList;
import rs.fon.plannerx.core.task.ports.out.tasklist.SaveTaskList;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskListJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.mapper.TaskListMapper;
import rs.fon.plannerx.infrastructure.persistence.tasks.repository.TaskListSpringDataRepository;

@RequiredArgsConstructor
@PersistenceAdapter
@Value
public class TaskListPersistenceAdapter implements SaveTaskList, GetTaskList, DeleteTaskList {

    TaskListSpringDataRepository taskListSpringDataRepository;

    TaskListMapper taskListMapper;

    @Override
    public TaskList saveAndReturn(TaskList taskList) {
        TaskListJpaEntity taskListJpaEntity = this.taskListSpringDataRepository.save(
                this.taskListMapper.mapToJpaEntity(taskList)
        );
        return taskListMapper.mapToEntity(taskListJpaEntity);
    }

    @Override
    public void save(TaskList taskList) {
        this.taskListSpringDataRepository.save(
                this.taskListMapper.mapToJpaEntity(taskList)
        );
    }

    @Override
    public TaskList get(int id) {
        return this.taskListMapper.mapToEntity(
                this.taskListSpringDataRepository.getById(id)
        );
    }

    @Override
    public void delete(TaskList taskList) {
        this.taskListSpringDataRepository.delete(this.taskListMapper.mapToJpaEntity(taskList));
    }
}
