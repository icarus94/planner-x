package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.CreateUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.CreateUserTaskListDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.SaveUserTaskList;

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
public class CreateUserTaskListUseCase implements CreateUserTaskList {

    private final SaveUserTaskList saveUserTaskListService;

    @Override
    public void create(CreateUserTaskListDto createUserTaskListDto) {
        // create user task list (aggregate)
        UserTaskList userTaskList = new UserTaskList();
        userTaskList.setUser(createUserTaskListDto.getUser());
        userTaskList.setTaskList(createUserTaskListDto.getTaskList());
        userTaskList.setOwner(createUserTaskListDto.isOwner());
        userTaskList.setPermission(createUserTaskListDto.getTaskListPermission());
        LocalDateTime localDateTime = LocalDateTime.now();
        userTaskList.setDateCreated(localDateTime);
        userTaskList.setDateModified(localDateTime);
        saveUserTaskListService.save(userTaskList);
    }
}
