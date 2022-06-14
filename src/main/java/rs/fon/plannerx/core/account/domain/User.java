package rs.fon.plannerx.core.account.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {
    private int id;
    private boolean active;
    private String email;
    private String name;
    private String surname;
    private String password;
    private UserRole role;
}
