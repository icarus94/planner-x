package rs.fon.plannerx.core.account.ports.out;

import rs.fon.plannerx.core.account.domain.User;

import java.util.Set;

public interface GetUsers {
    Set<User> getRegularUsersAsPagination(int page, int pageSize, String sortBy, String sortDirection);

    int getRegularUsersCount();
}
