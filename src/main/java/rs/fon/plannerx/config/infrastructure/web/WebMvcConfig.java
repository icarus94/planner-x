package rs.fon.plannerx.config.infrastructure.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {
    @Override
    public RequestToViewNameTranslator viewNameTranslator() {
        return new MyRequestToViewNameTranslator();
    }
}