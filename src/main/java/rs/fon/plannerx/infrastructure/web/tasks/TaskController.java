package rs.fon.plannerx.infrastructure.web.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import rs.fon.plannerx.common.WebAdapter;
import rs.fon.plannerx.core.task.domain.TaskPriority;
import rs.fon.plannerx.core.task.ports.in.task.CheckTask;
import rs.fon.plannerx.core.task.ports.in.task.CreateTask;
import rs.fon.plannerx.core.task.ports.in.task.DeleteTask;
import rs.fon.plannerx.core.task.ports.in.task.UpdateTask;
import rs.fon.plannerx.core.task.ports.in.task.dto.CheckTaskDto;
import rs.fon.plannerx.core.task.ports.in.task.dto.CreateTaskDto;
import rs.fon.plannerx.core.task.ports.in.task.dto.DeleteTaskDto;
import rs.fon.plannerx.core.task.ports.in.task.dto.UpdateTaskDto;
import rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory;
import rs.fon.plannerx.infrastructure.web.messages.Message;
import rs.fon.plannerx.infrastructure.web.security.CurrentUser;
import rs.fon.plannerx.infrastructure.web.security.UserPrincipal;
import rs.fon.plannerx.infrastructure.web.sitemap.SiteMap;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebAdapter
@Controller
@RequiredArgsConstructor
public class TaskController {

    private final FlashMessageFactory flashMessageFactory;

    private final CreateTask createTaskService;

    private final UpdateTask updateTaskService;

    private final DeleteTask deleteTaskService;

    private final CheckTask checkTaskService;

    @PostMapping(SiteMap.CREATE_TASK)
    public RedirectView createTask(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "taskListId") int taskListId,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate,
            @RequestParam(name = "taskPriority") TaskPriority taskPriority,
            RedirectAttributes redirectAttributes
    ) {
        createTaskService.create(
                new CreateTaskDto(
                        userPrincipal.getId(),
                        taskListId,
                        description,
                        dueDate,
                        taskPriority
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_ADDED)
        );
        return new RedirectView(SiteMap.MY_TASK_LISTS);
    }


    @PostMapping(SiteMap.UPDATE_TASK)
    public RedirectView updateTask(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "id") int taskId,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate,
            @RequestParam(name = "taskPriority") TaskPriority taskPriority,
            RedirectAttributes redirectAttributes
    ) {
        updateTaskService.update(
                new UpdateTaskDto(
                        userPrincipal.getId(),
                        taskId,
                        description,
                        dueDate,
                        taskPriority
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_UPDATED)
        );
        return new RedirectView(SiteMap.MY_TASK_LISTS);
    }

    @PostMapping(SiteMap.DELETE_TASK)
    public RedirectView deleteTask(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "id") int taskId,
            RedirectAttributes redirectAttributes
    ) {
        deleteTaskService.delete(
                new DeleteTaskDto(
                        userPrincipal.getId(),
                        taskId
                )
        );
        redirectAttributes.addFlashAttribute(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_UPDATED)
        );
        return new RedirectView(SiteMap.MY_TASK_LISTS);
    }

    @ResponseBody
    @PostMapping(value = SiteMap.CHECK_TASK, produces = "application/json")
    public ResponseEntity<Object> checkTask(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(name = "id") int taskId,
            @RequestParam(name = "done") boolean done
    ) {
        checkTaskService.check(
                new CheckTaskDto(
                        userPrincipal.getId(),
                        taskId,
                        done
                )
        );
        Map<String, Object> map = new HashMap<>();
        map.put(
                Message.PLACEHOLDER,
                this.flashMessageFactory.successFlashMessage(Message.TASK_CHECK_CHANGE)
        );
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
