package rs.fon.plannerx.application.web.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import rs.fon.plannerx.application.web.sitemap.SiteMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl(SiteMap.LOGIN_FAILED);
        if (exception.getClass().isAssignableFrom(DisabledException.class)) {
            setDefaultFailureUrl(SiteMap.LOGIN_FAILED_ACCOUNT_NOT_ACTIVE);
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
