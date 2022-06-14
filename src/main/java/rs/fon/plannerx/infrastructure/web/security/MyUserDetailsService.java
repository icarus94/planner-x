package rs.fon.plannerx.infrastructure.web.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.fon.plannerx.core.account.domain.User;
import rs.fon.plannerx.core.account.ports.in.GetUser;


@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final GetUser getUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserService.getUserByEmail(username);
        if (user == null) {            //todo check
            throw new UsernameNotFoundException(username);
        }
        return UserPrincipal.from(user);
    }
}
