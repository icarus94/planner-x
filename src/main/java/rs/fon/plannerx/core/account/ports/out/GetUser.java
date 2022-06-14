package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.domain.User;

public interface GetUser {
    User getUserByEmail(String email);

    User getUserById(int id);
}
