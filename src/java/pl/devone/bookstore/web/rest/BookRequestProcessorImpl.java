package pl.devone.bookstore.web.rest;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lukasz Jedrzynski on 07.01.2017.
 */
@Component
public class BookRequestProcessorImpl implements RestRequestProcessor {

  private Pattern regExAllPattern = Pattern.compile("/books");
  private Pattern regExIdPattern = Pattern.compile("/books/([0-9]*)");
  private Long id;

  public void process(HttpServletRequest request) throws ServletException {
    Matcher matcher;
    String requestUri = request.getRequestURI();
    matcher = regExIdPattern.matcher(requestUri);
    if (matcher.find()) {
      id = Long.parseLong(matcher.group(1));
      return;
    }
    matcher = regExAllPattern.matcher(requestUri);
    if (matcher.find()) return;
    throw new ServletException("Invalid URI : '" + requestUri + "'");
  }

  public Long getId() {
    return id;
  }

  public boolean isSetId() {
    return id != null;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
