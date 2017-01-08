package pl.devone.bookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.devone.bookstore.domain.Book;
import pl.devone.bookstore.repository.BookRepository;
import pl.devone.bookstore.service.dto.BookDTO;
import pl.devone.bookstore.service.mapper.BookMapper;

import java.util.List;

/**
 * Created by Lukasz Jedrzynski on 08.01.2017.
 */
@Service

public class BookService {

  private final Logger log = LoggerFactory.getLogger(BookService.class);

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookMapper bookMapper;

  public Book createBook(BookDTO bookDTO) {
    Book book = bookMapper.bookDTOToBook(bookDTO);
    log.debug("Saving new book: " + book.toString());
    return bookRepository.save(book);
  }

  public Book updateBook(BookDTO bookDTO) {
    Book book = null;
    if (bookRepository.exists(bookDTO.getId())) {
      book = bookMapper.bookDTOToBook(bookDTO);
      log.debug("Updating book: " + book.toString());
      bookRepository.save(book);
    }
    return book;
  }

  public BookDTO getBook(Long id) {
    log.debug("Fetching book with id: " + id);
    Book book = bookRepository.getOne(id);
    return bookMapper.bookToBookDTO(book);
  }

  public List<BookDTO> getAllBooks() {
    log.debug("Fetching all books");
    List<Book> books = bookRepository.findAll();
    return bookMapper.booksToBookDTOs(books);
  }

  public Book deleteBook(Long id) {
    log.debug("Deleting book with id: " + id);
    Book book = bookRepository.getOne(id);
    bookRepository.delete(book);
    return book;
  }

}
