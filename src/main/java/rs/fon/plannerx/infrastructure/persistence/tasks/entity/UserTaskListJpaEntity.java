package rs.fon.plannerx.infrastructure.persistence.tasks.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import rs.fon.plannerx.core.task.domain.TaskListPermission;
import rs.fon.plannerx.infrastructure.persistence.account.entity.UserJpaEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_task_list")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskListJpaEntity {

    @EmbeddedId
    UserTaskListJpaEntityPk userTaskListJpaEntityPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn
    UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taskListId")
    @JoinColumn
    TaskListJpaEntity taskList;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TaskListPermission taskListPermission;

    @NotNull
    @Column(nullable = false)
    boolean owner;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dateModified;
}
