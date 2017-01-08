package pl.devone.bookstore.web.servlet;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Lukasz Jedrzynski on 08.01.2017.
 */
public class RestServlet extends HttpServlet {

  private WebApplicationContext springContext;

  @Override
  public void init() throws ServletException {
    super.init();
    springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
    final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
    beanFactory.autowireBean(this);
  }
}
