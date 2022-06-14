package rs.fon.plannerx.infrastructure.web.tasks;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.task.domain.TaskListPermission;
import rs.fon.plannerx.core.task.ports.in.usertasklist.CreateSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.GetSharedUserTaskLists;
import rs.fon.plannerx.core.task.ports.in.usertasklist.RemoveSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.UpdateSharedUserTaskList;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.CreateSharedUserTaskListDto;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.RemoveUserFromSharedTaskListDto;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UpdateSharedUserTaskListDto;
import rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory;
import rs.fon.plannerx.infrastructure.web.messages.Message;
import rs.fon.plannerx.infrastructure.web.security.CurrentUser;
import rs.fon.plannerx.infrastructure.web.security.UserPrincipal;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

@WebAdapter
@Controller
@AllArgsConstructor
public class ShareTaskListController {

    private final GetSharedUserTaskLists getSharedUserTaskListsService;
    private final CreateSharedUserTaskList createSharedUserTaskListService;
    private final UpdateSharedUserTaskList updateSharedUserTaskListService;
    private final RemoveSharedUserTaskList removeSharedUserTaskListService;
    private final FlashMessageFactory flashMessageFactory;


    @GetMapping(SiteMap.SHARE_TASKS)
    public void getPage(
            @CurrentUser UserPrincipal userPrincipal,
            Model model
    ) {
        model.addAttribute(
                "sharedUserTaskLists",
                getSharedUserTaskListsService.getShared(userPrincipal.getId())
        );
    }

    @PostMapping(SiteMap.CREATE_SHARED_USER_TASK_LIST)
    public RedirectView shareTaskList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "taskListId") int targetTaskListId,
            @RequestParam(name = "taskListPermission") TaskListPermission taskListPermission,
            RedirectAttributes redirectAttributes
    ) {
        this.createSharedUserTaskListService.create(
                new CreateSharedUserTaskListDto(
                        userPrincipal.getId(),
                        email,
                        targetTaskListId,
                        taskListPermission
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.SHARE_TASK_LIST_SUCCESS)
        );
        return new RedirectView(SiteMap.SHARE_TASKS);
    }

    @PostMapping(SiteMap.UPDATE_SHARED_USER_TASK_LIST)
    public RedirectView updateSharedUserTaskList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "userId") int targetUserId,
            @RequestParam(name = "taskListId") int targetTaskListId,
            @RequestParam(name = "taskListPermission") TaskListPermission taskListPermission,
            RedirectAttributes redirectAttributes
    ) {
        this.updateSharedUserTaskListService.update(
                new UpdateSharedUserTaskListDto(
                        userPrincipal.getId(),
                        targetUserId,
                        targetTaskListId,
                        taskListPermission
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.UPDATE_SHARED_USER_TASK_LIST)
        );
        return new RedirectView(SiteMap.SHARE_TASKS);
    }

    @PostMapping(SiteMap.REMOVE_SHARED_USER_TASK_LIST)
    public RedirectView removeUserFromSharedTaskList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "userId") int targetUserId,
            @RequestParam(name = "taskListId") int targetTaskListId,
            RedirectAttributes redirectAttributes
    ) {
        this.removeSharedUserTaskListService.remove(
                new RemoveUserFromSharedTaskListDto(
                        userPrincipal.getId(),
                        targetUserId,
                        targetTaskListId
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.REMOVE_USER_FROM_TASK_LIST)
        );
        return new RedirectView(SiteMap.SHARE_TASKS);
    }
}
