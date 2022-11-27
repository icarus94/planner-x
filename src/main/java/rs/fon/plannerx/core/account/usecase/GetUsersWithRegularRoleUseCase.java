package rs.fon.plannerx.core.account.usecase;

import lombok.AllArgsConstructor;
import rs.fon.plannerx.common.UseCase;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUsersWithRegularRole;
import rs.fon.plannerx.core.account.ports.in.dto.PaginationDto;

import java.util.Set;

@UseCase
@AllArgsConstructor
public class GetUsersWithRegularRoleUseCase implements GetUsersWithRegularRole {

    private final rs.fon.plannerx.core.account.ports.out.GetUsers getUsersService;

    @Override
    public Set<User> getPaginated(PaginationDto paginationDto) {
        return getUsersService.getRegularUsers(
                paginationDto.getPage(),
                paginationDto.getPageSize(),
                paginationDto.getSortBy(),
                paginationDto.getSortDirection()
        );
    }

    @Override
    public int getTotalCount() {
        return getUsersService.getRegularUsersCount();
    }
}
