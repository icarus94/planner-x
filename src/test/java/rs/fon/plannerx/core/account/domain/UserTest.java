package rs.fon.plannerx.core.account.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUserInstanceViaConstructor() {
        User user = new User(
                1,
                true,
                "test@test.com",
                "name",
                "surname",
                "password1",
                UserRole.ROLE_REGULAR
        );

        Assertions.assertEquals(1, user.getId());
        Assertions.assertTrue(user.isActive());
        Assertions.assertEquals("test@test.com", user.getEmail());
        Assertions.assertEquals("name", user.getName());
        Assertions.assertEquals("surname", user.getSurname());
        Assertions.assertEquals("password1", user.getPassword());
        Assertions.assertEquals(UserRole.ROLE_REGULAR, user.getRole());
    }

    @Test
    public void testUserInstanceViaSetter() {
        User user = new User();
        user.setId(1);
        user.setActive(true);
        user.setEmail("test@test.com");
        user.setName("name");
        user.setSurname("surname");
        user.setPassword("password1");
        user.setRole(UserRole.ROLE_ADMIN);

        Assertions.assertEquals(1, user.getId());
        Assertions.assertTrue(user.isActive());
        Assertions.assertEquals("test@test.com", user.getEmail());
        Assertions.assertEquals("name", user.getName());
        Assertions.assertEquals("surname", user.getSurname());
        Assertions.assertEquals("password1", user.getPassword());
        Assertions.assertEquals(UserRole.ROLE_ADMIN, user.getRole());
    }
}
