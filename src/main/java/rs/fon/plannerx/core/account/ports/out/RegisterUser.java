package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.domain.User;

public interface RegisterUser {
    void register(User user);

    boolean isEmailAlreadyUsed(String email);
}
