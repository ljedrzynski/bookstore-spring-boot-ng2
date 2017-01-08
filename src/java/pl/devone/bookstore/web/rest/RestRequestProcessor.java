package pl.devone.bookstore.web.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lukasz Jedrzynski on 07.01.2017.
 */

public interface RestRequestProcessor {

  void process(HttpServletRequest httpServletRequest) throws ServletException;

  Long getId();

  public boolean isSetId();

  void setId(Long id);
}
