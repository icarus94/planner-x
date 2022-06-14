package rs.fon.plannerx.core.task.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    private int id;
    private String description;
    private LocalDateTime dueDate;
    private boolean done;
    private TaskPriority taskPriority;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
    private TaskList taskList;
}
