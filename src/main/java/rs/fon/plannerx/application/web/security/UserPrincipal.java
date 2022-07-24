package rs.fon.plannerx.application.web.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.fon.plannerx.core.account.domain.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class UserPrincipal implements UserDetails {

    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean active;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal from(User user) {
        Set<GrantedAuthority> grantAuths = new HashSet<>();
        grantAuths.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                grantAuths
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
