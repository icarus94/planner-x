package rs.fon.plannerx.core.task.ports.in.task.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import rs.fon.plannerx.common.SelfValidating;
import rs.fon.plannerx.core.task.domain.TaskPriority;

import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateTaskDto extends SelfValidating<UpdateTaskDto> {
    @NotNull
    @Digits(fraction = 0, integer = 12)
    int userId;

    @NotNull
    @Digits(fraction = 0, integer = 12)
    int taskId;

    @NotNull
    @NotBlank(message = "task.description")
    String description;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @FutureOrPresent(message = "task.present_or_future")
    LocalDateTime dueDate;

    @NotNull
    TaskPriority taskPriority;

    public UpdateTaskDto(int userId, int taskId, String description, LocalDateTime dueDate, TaskPriority taskPriority) {
        this.userId = userId;
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.taskPriority = taskPriority;
        this.validateSelf();
    }
}
