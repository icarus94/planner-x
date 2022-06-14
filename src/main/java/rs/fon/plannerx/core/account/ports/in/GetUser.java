package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.domain.User;

public interface GetUser {
    User getUserByEmail(String email);

    User getUserById(int id);
}
