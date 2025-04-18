package rs.fon.plannerx.infrastructure.persistence.tasks.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import rs.fon.plannerx.core.task.domain.TaskPriority;
import rs.fon.plannerx.infrastructure.persistence.account.entity.UserJpaEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    int id;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    LocalDateTime dueDate;

    @Column(nullable = false)
    boolean done;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TaskPriority taskPriority;

    @CreatedDate
    @Column(nullable = false)
    LocalDateTime dateAdded;

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime dateModified;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "done_by_user_id", nullable = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    UserJpaEntity doneBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id", nullable = false)
    TaskListJpaEntity taskList;
}
