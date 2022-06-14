package rs.fon.plannerx.config.infrastructure.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class Internationalization implements WebMvcConfigurer {
    @Bean("messageSource")
    public MessageSource messageSource() {
        String[] basename = {
                "classpath:/i18n/action/account/homepage",
                "classpath:/i18n/action/account/invite-user",
                "classpath:/i18n/action/account/my-account",
                "classpath:/i18n/action/account/login",
                "classpath:/i18n/action/account/register",

                "classpath:/i18n/action/admin/homepage",
                "classpath:/i18n/action/admin/user-info",
                "classpath:/i18n/action/admin/users",

                "classpath:/i18n/action/task/modal"
        };
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(basename);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheMillis(1000);
        return messageSource;
    }

    @Bean
    public LocaleResolver myLocaleResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }
}
