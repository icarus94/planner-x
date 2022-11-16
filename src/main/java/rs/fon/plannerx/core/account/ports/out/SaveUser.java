package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.domain.User;

public interface SaveUser {
    void save(User user);
}
