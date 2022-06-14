package rs.fon.plannerx.infrastructure.persistence.tasks.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskListJpaEntityPk implements Serializable {
    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int taskListId;
}
