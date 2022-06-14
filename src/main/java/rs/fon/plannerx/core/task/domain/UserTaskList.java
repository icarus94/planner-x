package rs.fon.plannerx.core.task.domain;

import lombok.*;
import rs.fon.plannerx.core.account.domain.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserTaskList {
    private User user;
    private TaskList taskList;
    private TaskListPermission permission;
    private boolean owner;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
