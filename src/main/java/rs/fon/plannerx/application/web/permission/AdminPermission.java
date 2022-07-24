package rs.fon.plannerx.application.web.permission;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@PreAuthorize(value = "hasRole(T(rs.fon.plannerx.core.account.domain.UserRole).ROLE_ADMIN.name())")
public @interface AdminPermission {
}
