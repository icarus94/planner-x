package rs.fon.plannerx.infrastructure.persistence.account.mapper;

import org.springframework.stereotype.Component;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.infrastructure.persistence.account.entity.UserJpaEntity;

@Component
public class UserMapper {

    public User mapToEntity(UserJpaEntity userJpaEntity) {
        if (userJpaEntity == null) {
            return null;
        }
        return new User(
                userJpaEntity.getId(),
                userJpaEntity.isActive(),
                userJpaEntity.getEmail(),
                userJpaEntity.getName(),
                userJpaEntity.getSurname(),
                userJpaEntity.getPassword(),
                userJpaEntity.getRole(),
                userJpaEntity.getVerificationToken()
        );
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        if (user == null) {
            return null;
        }

        return new UserJpaEntity(
                user.getId(),
                user.isActive(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                user.getSurname(),
                user.getRole(),
                user.getVerificationToken()
        );
    }
}                                     
