package rs.fon.plannerx.application.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import javax.servlet.http.HttpServletRequest;

public class MyRequestToViewNameTranslator implements RequestToViewNameTranslator {

    public final Logger logger = LoggerFactory.getLogger(MyRequestToViewNameTranslator.class);
    private static final String viewFolderPrefix = "action";

    @Override
    public String getViewName(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String view = removeQueryStrings(requestUri);
        outputMapping(requestUri, viewFolderPrefix + view);
        return viewFolderPrefix + view;
    }

    private String removeQueryStrings(String url) {
        int index = url.indexOf("?");
        if (index == -1) {
            return url;
        }
        return url.substring(0, index);
    }

    private void outputMapping(String url, String view) {
        this.logger.info("Mapping URI => VIEW | {} => {}", url, view);
    }
}
