package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.domain.User;

public interface GetUser {
    User getByEmail(String email);

    User getById(int id);

    User getByToken(String token);
}
