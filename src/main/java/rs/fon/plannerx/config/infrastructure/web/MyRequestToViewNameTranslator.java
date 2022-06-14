package rs.fon.plannerx.config.infrastructure.web;

import org.springframework.web.servlet.RequestToViewNameTranslator;

import javax.servlet.http.HttpServletRequest;

public class MyRequestToViewNameTranslator implements RequestToViewNameTranslator {

    private static final String viewFolderPrefix = "action";

    @Override
    public String getViewName(HttpServletRequest request) throws Exception {
        String requestUri = request.getRequestURI();
        String urlWithoutQueryString = removeQueryStrings(requestUri);
        String view = transformUrlToView(urlWithoutQueryString);
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

    private String transformUrlToView(String url) {
        return url;
//        return url.replace('-', '.');
    }

    private void outputMapping(String url, String view) {
        System.out.printf("Mapping URL => VIEW | %s => %s%n", url, view);
    }

}
