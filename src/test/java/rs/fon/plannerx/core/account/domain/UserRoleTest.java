package rs.fon.plannerx.core.account.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRoleTest {
    @Test
    public void testUserRoleTypeAdmin() {
        UserRole userRole = UserRole.ROLE_ADMIN;
        Assertions.assertEquals("Admin", userRole.getRoleName());
    }

    @Test
    public void testUserRoleTypeRegular() {
        UserRole userRole = UserRole.ROLE_REGULAR;
        Assertions.assertEquals("Regular User", userRole.getRoleName());
    }
}
