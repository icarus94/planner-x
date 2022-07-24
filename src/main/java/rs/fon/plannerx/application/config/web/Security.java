package rs.fon.plannerx.application.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import rs.fon.plannerx.application.web.security.CustomAuthenticationFailureHandler;
import rs.fon.plannerx.application.web.security.CustomAuthenticationSuccessHandler;
import rs.fon.plannerx.application.web.security.MyUserDetailsService;
import rs.fon.plannerx.application.web.sitemap.SiteMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailsService;

    public Security(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers(
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**"
                )
                .permitAll()
                .and()
                .authorizeRequests()
                .mvcMatchers(SiteMap.LOGIN, SiteMap.REGISTER, SiteMap.LOGIN_FAILED)
                .anonymous()
                .and()
                .formLogin()
                .loginPage(SiteMap.LOGIN)
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureUrl(SiteMap.LOGIN_FAILED)
                .failureHandler(new CustomAuthenticationFailureHandler())
                .permitAll()
                .and()
                .csrf().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher(SiteMap.LOGOUT))
                .logoutSuccessUrl(SiteMap.LOGOUT_SUCCESS)
                .permitAll()

                //** Deny everything else **//
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setDefaultRolePrefix("");
        return defaultWebSecurityExpressionHandler;
    }
}
