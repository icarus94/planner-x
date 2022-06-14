package rs.fon.plannerx.core.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserRole {
    ROLE_ADMIN("Admin"),
    ROLE_REGULAR("Regular User");

    private final String roleName;

    @Override
    public String toString() {
        return this.roleName;
    }
}
