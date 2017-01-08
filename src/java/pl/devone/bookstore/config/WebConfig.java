package pl.devone.bookstore.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.devone.bookstore.web.servlet.BookServlet;

/**
 * Created by Lukasz Jedrzynski on 07.01.2017.
 */
@Configuration
public class WebConfig {
  @Bean
  public ServletRegistrationBean bookServletRegistrationBean() {
    return new ServletRegistrationBean(new BookServlet(), "/api/books/*");
  }
}
