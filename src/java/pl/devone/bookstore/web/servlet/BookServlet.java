package pl.devone.bookstore.web.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.devone.bookstore.domain.Book;
import pl.devone.bookstore.service.BookService;
import pl.devone.bookstore.service.dto.BookDTO;
import pl.devone.bookstore.web.rest.BookRequestProcessorImpl;
import pl.devone.bookstore.web.rest.RestRequestProcessor;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


/**
 * Created by Lukasz Jedrzynski on 07.01.2017.
 */
public class BookServlet extends RestServlet {

  private final Logger log = LoggerFactory.getLogger(BookServlet.class);

  @Autowired
  private Gson gson;

  @Autowired
  private BookService bookService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    RestRequestProcessor processor = new BookRequestProcessorImpl();
    if (!isRequestValid(req, resp, processor)) {
      return;
    }
    try {
      String json;
      if (processor.isSetId()) {
        BookDTO bookDTO = bookService.getBook(processor.getId());
        json = gson.toJson(bookDTO);
      } else {
        List<BookDTO> bookMap = bookService.getAllBooks();
        json = gson.toJson(bookMap);
      }
      resp.getWriter().write(json);
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (EntityNotFoundException exc) {
      resp.getWriter().write(gson.toJson(exc.getMessage()));
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      log.debug(exc.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RestRequestProcessor processor = new BookRequestProcessorImpl();
    if (!isRequestValid(req, resp, processor)) {
      return;
    }
    StringBuilder stringBuffer = new StringBuilder();
    String line;
    try {
      BufferedReader reader = req.getReader();
      while ((line = reader.readLine()) != null)
        stringBuffer.append(line);
    } catch (Exception exc) {
      resp.getWriter().write(exc.getMessage());
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    try {
      BookDTO bookDTO = gson.fromJson(stringBuffer.toString(), BookDTO.class);
      Book book = bookService.createBook(bookDTO);
      log.debug("Created new book:" + book.toString());
      resp.setStatus(HttpServletResponse.SC_OK);
      resp.setHeader("Location", "/books/" + book.getId());
    } catch (JsonSyntaxException exc) {
      resp.getWriter().write(exc.getMessage());
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      log.debug(exc.getMessage());
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RestRequestProcessor processor = new BookRequestProcessorImpl();
    if (!isRequestValid(req, resp, processor)) {
      return;
    }
    StringBuilder stringBuffer = new StringBuilder();
    String line;
    try {
      BufferedReader reader = req.getReader();
      while ((line = reader.readLine()) != null)
        stringBuffer.append(line);
    } catch (Exception exc) {
      resp.getWriter().write(exc.getMessage());
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    if (processor.isSetId()) {
      try {
        BookDTO bookDTO = gson.fromJson(stringBuffer.toString(), BookDTO.class);
        Book book = bookService.updateBook(bookDTO);
        log.debug("Updated book:" + book.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
      } catch (JsonSyntaxException exc) {
        resp.getWriter().write(exc.getMessage());
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        log.debug(exc.getMessage());
      }
    } else {
      resp.getWriter().write(gson.toJson("Cannot delete all collection at once"));
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RestRequestProcessor processor = new BookRequestProcessorImpl();
    if (!isRequestValid(req, resp, processor)) {
      return;
    }
    if (processor.isSetId()) {
      try {
        bookService.deleteBook(processor.getId());
      } catch (EntityNotFoundException exc) {
        resp.getWriter().write(gson.toJson(exc.getMessage()));
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    } else {
      resp.getWriter().write(gson.toJson("Cannot delete all collection at once"));
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private boolean isRequestValid(HttpServletRequest request, HttpServletResponse response, RestRequestProcessor processor) throws IOException {
    try {
      processor.process(request);
    } catch (ServletException exc) {
      response.getWriter().write(gson.toJson(exc.getMessage()));
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      log.debug(exc.getMessage());
      return false;
    }
    return true;
  }
}
