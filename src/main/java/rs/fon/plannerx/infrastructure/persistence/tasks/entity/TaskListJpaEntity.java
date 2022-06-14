package rs.fon.plannerx.infrastructure.persistence.tasks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "task_list")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskListJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    int id;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 250)
    String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "taskList", fetch = FetchType.EAGER)
    Set<TaskJpaEntity> tasks;
}
