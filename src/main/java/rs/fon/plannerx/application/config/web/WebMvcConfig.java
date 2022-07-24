package rs.fon.plannerx.application.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {
    @Override
    @NonNull
    public RequestToViewNameTranslator viewNameTranslator() {
        return new MyRequestToViewNameTranslator();
    }
}