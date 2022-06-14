package rs.fon.plannerx.infrastructure.persistence.account.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.fon.plannerx.core.account.domain.UserRole;
import rs.fon.plannerx.infrastructure.persistence.account.entity.UserJpaEntity;

import java.util.List;

public interface UserSpringDataRepository extends JpaRepository<UserJpaEntity, Integer> {
    UserJpaEntity findFirstByEmail(String email);

    boolean existsByEmail(String email);

    int countAllByRole(UserRole userRole);

    @Query(value = "FROM UserJpaEntity WHERE role = rs.fon.plannerx.core.account.domain.UserRole.ROLE_REGULAR")
    List<UserJpaEntity> findAllWithRegularRole(Pageable pageable);
}
