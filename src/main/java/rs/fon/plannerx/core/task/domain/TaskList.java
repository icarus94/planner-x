package rs.fon.plannerx.core.task.domain;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TaskList {
    private int id;
    private String name;
    private Set<Task> tasks;
}
