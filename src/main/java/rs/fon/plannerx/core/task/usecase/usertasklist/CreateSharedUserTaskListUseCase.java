package rs.fon.plannerx.core.task.usecase.usertasklist;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.out.GetUser;
import rs.fon.plannerx.core.exception.CoreDomainException;
import rs.fon.plannerx.core.task.domain.UserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.CreateSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.CreateSharedUserTaskListDto;
import rs.fon.plannerx.core.task.ports.out.usertasklist.GetUserTaskList;
import rs.fon.plannerx.core.task.ports.out.usertasklist.SaveUserTaskList;

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
public class CreateSharedUserTaskListUseCase implements CreateSharedUserTaskList {
    private final GetUserTaskList getUserTaskListService;
    private final SaveUserTaskList saveUserTaskListService;
    private final GetUser getUserService;

    @Override
    public void create(CreateSharedUserTaskListDto createSharedUserTaskListDto) {
        UserTaskList userTaskList = getUserTaskListService.get(
                createSharedUserTaskListDto.getInvokerUserId(),
                createSharedUserTaskListDto.getTargetTaskListId()
        );
        if (!userTaskList.isOwner()) {
            throw new CoreDomainException("Current user doesn't own this task list");
        }
        User user = this.getUserService.getByEmail(createSharedUserTaskListDto.getTargetEmail());
        if (user == null) {
            throw new CoreDomainException("User with given email does not exist");
        }
        UserTaskList sharedUserTaskList = new UserTaskList();
        sharedUserTaskList.setUser(user);
        sharedUserTaskList.setTaskList(userTaskList.getTaskList());
        sharedUserTaskList.setPermission(createSharedUserTaskListDto.getTaskListPermission());
        sharedUserTaskList.setOwner(false);
        LocalDateTime localDateTime = LocalDateTime.now();
        sharedUserTaskList.setDateCreated(localDateTime);
        sharedUserTaskList.setDateModified(localDateTime);

        saveUserTaskListService.save(sharedUserTaskList);
    }
}
