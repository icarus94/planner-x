package rs.fon.plannerx.core.account.ports.in;

import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.dto.PaginationDto;

import java.util.Set;

public interface GetUsersWithRegularRole {
    Set<User> getPaginated(PaginationDto paginationDto);

    int getTotalCount();
}
