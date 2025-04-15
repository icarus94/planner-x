package rs.fon.plannerx.infrastructure.persistence.account.entity;

import lombok.*;
import rs.fon.plannerx.core.account.domain.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_userjpaentity_email", columnList = "email")
})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @NotNull
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 30)
    private String name;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 300)
    private String password;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 30)
    private String surname;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String verificationToken;
}
