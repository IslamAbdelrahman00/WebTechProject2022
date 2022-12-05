package de.htwberlin.webtech.webproject.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Frontend pages
        registry.addViewController(Endpoints.Site.INDEX).setViewName(ViewNames.INDEX);
        registry.addViewController(Endpoints.Site.SLASH_INDEX).setViewName(ViewNames.INDEX);
        registry.addViewController(Endpoints.Site.ALL_LISTS).setViewName(ViewNames.ALL_LISTS);
        registry.addViewController(Endpoints.Site.LIST).setViewName(ViewNames.LIST);
        registry.addViewController(Endpoints.Site.LIST).setViewName(ViewNames.POST_LIST);
        registry.addViewController(Endpoints.Site.ABOUT).setViewName(ViewNames.ABOUT);
        registry.addViewController(Endpoints.Site.CONTACT).setViewName(ViewNames.CONTACT);
    }


}
