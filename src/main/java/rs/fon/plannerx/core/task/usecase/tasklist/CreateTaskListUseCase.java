package rs.fon.plannerx.core.task.usecase.tasklist;

import lombok.RequiredArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.ports.in.GetUser;
import rs.fon.plannerx.core.task.domain.TaskList;
import rs.fon.plannerx.core.task.domain.TaskListPermission;
import rs.fon.plannerx.core.task.ports.in.tasklist.CreateTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.CreateTaskListDto;
import rs.fon.plannerx.core.task.ports.in.usertasklist.CreateUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.CreateUserTaskListDto;
import rs.fon.plannerx.core.task.ports.out.tasklist.SaveTaskList;

import java.util.LinkedHashSet;

@UseCase
@RequiredArgsConstructor
public class CreateTaskListUseCase implements CreateTaskList {

    private final SaveTaskList saveTaskListService;
    private final GetUser getUserService;
    private final CreateUserTaskList createUserTaskListService;

    @Override
    public void create(CreateTaskListDto createTaskListDto) {
        // create task list
        TaskList taskList = new TaskList();
        taskList.setName(createTaskListDto.getName());
        taskList.setTasks(new LinkedHashSet<>());
        taskList = saveTaskListService.saveAndReturn(taskList);

        // create user task list (aggregate)
        createUserTaskListService.create(
                new CreateUserTaskListDto(
                        getUserService.getById(createTaskListDto.getUserId()),
                        taskList,
                        true,
                        TaskListPermission.ALL
                )
        );
    }
}
