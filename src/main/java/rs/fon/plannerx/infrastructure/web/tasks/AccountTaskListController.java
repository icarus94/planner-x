package rs.fon.plannerx.infrastructure.web.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.task.ports.in.tasklist.CreateTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.DeleteTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.UpdateTaskList;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.CreateTaskListDto;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.DeleteTaskListDto;
import rs.fon.plannerx.core.task.ports.in.tasklist.dto.UpdateTaskListDto;
import rs.fon.plannerx.core.task.ports.in.usertasklist.GetUserTaskLists;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.TaskOrderByFilter;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.TaskStatusFilter;
import rs.fon.plannerx.core.task.ports.in.usertasklist.dto.UserTaskListFilterDto;
import rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory;
import rs.fon.plannerx.infrastructure.web.messages.Message;
import rs.fon.plannerx.infrastructure.web.security.CurrentUser;
import rs.fon.plannerx.infrastructure.web.security.UserPrincipal;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

@WebAdapter
@Controller
@RequiredArgsConstructor
public class AccountTaskListController {

    private final GetUserTaskLists getUserTaskListsService;

    private final CreateTaskList createTaskListService;

    private final UpdateTaskList updateTaskListService;

    private final DeleteTaskList deleteTaskListService;

    private final FlashMessageFactory flashMessageFactory;

    @GetMapping(SiteMap.MY_TASK_LISTS)
    public void getPage(
            @RequestParam(name = "orderBy", defaultValue = TaskOrderByFilter.DEFAULT_STATE) TaskOrderByFilter taskOrderByFilter,
            @RequestParam(name = "taskStatus", defaultValue = TaskStatusFilter.DEFAULT_STATE) TaskStatusFilter taskStatusFilter,
            @CurrentUser UserPrincipal userPrincipal,
            Model model
    ) {
        UserTaskListFilterDto userTaskListFilterDto = new UserTaskListFilterDto(
                taskOrderByFilter,
                taskStatusFilter
        );
        model.addAttribute("filter", userTaskListFilterDto);
        model.addAttribute(
                "userTaskLists",
                getUserTaskListsService.getUserTaskLists(
                        userPrincipal.getId(),
                        userTaskListFilterDto
                )
        );
    }

    @PostMapping(SiteMap.CREATE_TASK_LIST)
    public RedirectView createTaskList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "name") String name,
            RedirectAttributes redirectAttributes
    ) {
        createTaskListService.create(new CreateTaskListDto(
                userPrincipal.getId(),
                name
        ));
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_LIST_ADDED)
        );
        return new RedirectView(SiteMap.MY_TASK_LISTS);
    }

    @PostMapping(SiteMap.UPDATE_TASK_LIST)
    public RedirectView updateTaskList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "id") int id,
            @RequestParam(name = "name") String name,
            RedirectAttributes redirectAttributes
    ) {
        updateTaskListService.update(
                new UpdateTaskListDto(
                        userPrincipal.getId(),
                        id,
                        name
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_LIST_UPDATED)
        );
        return new RedirectView(SiteMap.MY_TASK_LISTS);
    }

    @PostMapping(SiteMap.DELETE_TASK_LIST)
    public RedirectView deleteTaskList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "id") int id,
            RedirectAttributes redirectAttributes
    ) {
        deleteTaskListService.delete(
                new DeleteTaskListDto(
                        userPrincipal.getId(),
                        id
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_LIST_DELETED)
        );
        return new RedirectView(SiteMap.MY_TASK_LISTS);
    }
}
