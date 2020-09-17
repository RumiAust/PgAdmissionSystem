package aust.iums.pg.admission.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Rumi on 9/16/2020.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/applicationForm");
    registry.addViewController("/notFound").setViewName("forward:/index.html");
  }

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
    return container -> {
      container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
          "/notFound"));
    };
  }

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> container() {
    return container -> {
      container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,
          "/notFound"));
    };
  }

}
