package rs.fon.plannerx.infrastructure.persistence.tasks.adapter;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Sort;
import rs.fon.plannerx.common.PersistenceAdapter;
import rs.fon.plannerx.core.task.domain.Task;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UserTaskListFilterDto;
import rs.fon.plannerx.core.task.ports.out.task.DeleteTask;
import rs.fon.plannerx.core.task.ports.out.task.GetTask;
import rs.fon.plannerx.core.task.ports.out.task.SaveTask;
import rs.fon.plannerx.infrastructure.persistence.tasks.entity.TaskJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.tasks.mapper.TaskMapper;
import rs.fon.plannerx.infrastructure.persistence.tasks.repository.TaskSpringDataRepository;

import java.util.Collection;

@RequiredArgsConstructor
@PersistenceAdapter
@Value
public class TaskPersistenceAdapter implements SaveTask, GetTask, DeleteTask {
    TaskMapper taskMapper;
    TaskSpringDataRepository taskSpringDataRepository;

    @Override
    public void save(Task task) {
        this.taskSpringDataRepository.save(
                this.taskMapper.mapToJpaEntity(task)
        );
    }

    @Override
    public Task get(int id) {
        return this.taskMapper.mapToEntity(
                this.taskSpringDataRepository.getById(id)
        );
    }

    @Override
    public void delete(Task task) {
        this.taskSpringDataRepository.delete(
                this.taskMapper.mapToJpaEntity(task)
        );
    }

    Collection<TaskJpaEntity> getByFilter(UserTaskListFilterDto userTaskListFilterDto, int taskListId) {
        Sort sort;
        switch (userTaskListFilterDto.getTaskOrderByFilter()) {
            case DATE_ASC:
                sort = Sort.by("dueDate").ascending();
                break;
            case DATE_DESC:
                sort = Sort.by("dueDate").descending();
                break;
            case PRIORITY_ASC:
                sort = Sort.by("taskPriority").ascending();
                break;
            case PRIORITY_DESC:
                sort = Sort.by("taskPriority").descending();
                break;
            default:
                sort = Sort.by("id").ascending();
        }
        Collection<TaskJpaEntity> tasks;
        switch (userTaskListFilterDto.getTaskStatusFilter()) {
            case FINISHED_TASKS:
                tasks = taskSpringDataRepository.findTasksByDoneTrueAndTaskList_Id(taskListId, sort);
                break;
            case UNFINISHED_TASKS:
                tasks = taskSpringDataRepository.findTasksByDoneFalseAndTaskList_Id(taskListId, sort);
                break;
            default:
                tasks = taskSpringDataRepository.findAllTasks(taskListId, sort);
        }
        return tasks;
    }
}
