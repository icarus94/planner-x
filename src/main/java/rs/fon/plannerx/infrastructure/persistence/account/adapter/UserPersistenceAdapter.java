package rs.fon.plannerx.infrastructure.persistence.account.adapter;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import rs.fon.plannerx.common.PersistenceAdapter;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.domain.UserRole;
import rs.fon.plannerx.core.account.ports.out.GetUser;
import rs.fon.plannerx.core.account.ports.out.GetUsers;
import rs.fon.plannerx.core.account.ports.out.RegisterUser;
import rs.fon.plannerx.core.account.ports.out.UpdateUser;
import rs.fon.plannerx.infrastructure.persistence.account.entity.UserJpaEntity;
import rs.fon.plannerx.infrastructure.persistence.account.mapper.UserMapper;
import rs.fon.plannerx.infrastructure.persistence.account.repository.UserSpringDataRepository;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter implements GetUser, RegisterUser, UpdateUser, GetUsers {

    private final UserSpringDataRepository userSpringDataRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        UserJpaEntity userJpaEntity = userSpringDataRepository.findFirstByEmail(email);
        return userMapper.mapToEntity(userJpaEntity);
    }

    @Override
    public User getUserById(int id) {
        UserJpaEntity userJpaEntity = userSpringDataRepository.getById(id);
        return userMapper.mapToEntity(userJpaEntity);
    }

    @Override
    public void register(User user) {
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        userSpringDataRepository.save(userJpaEntity);
    }

    @Override
    public boolean isEmailAlreadyUsed(String email) {
        return userSpringDataRepository.existsByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        userSpringDataRepository.save(userJpaEntity);
    }

    @Override
    public Set<User> getRegularUsersAsPagination(int page, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(sortBy).ascending();
        if (sortDirection.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        PageRequest pageable = PageRequest.of(page, pageSize, sort);
        return userSpringDataRepository.findAllWithRegularRole(pageable).stream()
                .map(userMapper::mapToEntity)
                .collect(Collectors.toSet());
    }

    @Override

    public int getRegularUsersCount() {
        return userSpringDataRepository.countAllByRole(UserRole.ROLE_REGULAR);
    }
}
