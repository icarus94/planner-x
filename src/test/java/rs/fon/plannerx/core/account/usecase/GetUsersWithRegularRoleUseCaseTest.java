package rs.fon.plannerx.core.account.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.dto.PaginationDto;
import rs.fon.plannerx.core.account.ports.out.GetUsers;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.mockito.BDDMockito.*;

public class GetUsersWithRegularRoleUseCaseTest {
    private GetUsers getUsersService;

    @BeforeEach
    void init() {
        getUsersService = Mockito.mock(GetUsers.class);
    }

    @Test
    public void test_getTotalCount() {

        given(getUsersService.getRegularUsersCount()).willReturn(20);

        GetUsersWithRegularRoleUseCase getUsersUseCase = new GetUsersWithRegularRoleUseCase(getUsersService);
        // act
        int result = getUsersUseCase.getTotalCount();

        Assertions.assertEquals(20, result);
    }

    @Test
    public void test_getPaginated() {
        PaginationDto paginationDto = new PaginationDto(1, 1, "name", "asc");
        User userMocked = Mockito.mock(User.class);
        Set<User> usersMock = new LinkedHashSet<>();
        usersMock.add(userMocked);

        given(getUsersService.getRegularUsers(
                eq(paginationDto.getPage()),
                eq(paginationDto.getPageSize()),
                eq(paginationDto.getSortBy()),
                eq(paginationDto.getSortDirection())
        )).willReturn(usersMock);

        GetUsersWithRegularRoleUseCase getUsersUseCase = new GetUsersWithRegularRoleUseCase(getUsersService);
        // act
        Set<User> result = getUsersUseCase.getPaginated(paginationDto);

        Assertions.assertEquals(usersMock, result);
        verify(getUsersService, times(1)).getRegularUsers(anyInt(), anyInt(), anyString(), anyString());
    }
}
