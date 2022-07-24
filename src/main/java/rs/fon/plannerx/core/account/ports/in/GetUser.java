package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.domain.User;

public interface GetUser {
    User getByEmail(String email);

    User getById(int id);
}
