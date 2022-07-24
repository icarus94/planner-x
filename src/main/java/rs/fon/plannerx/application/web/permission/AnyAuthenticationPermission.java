package rs.fon.plannerx.application.web.permission;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@PreAuthorize(value = "isAuthenticated()")
public @interface AnyAuthenticationPermission {
}
