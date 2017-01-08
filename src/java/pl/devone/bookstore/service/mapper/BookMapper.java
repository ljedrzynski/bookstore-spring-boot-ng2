package pl.devone.bookstore.service.mapper;

import org.mapstruct.Mapper;
import pl.devone.bookstore.domain.Book;
import pl.devone.bookstore.service.dto.BookDTO;

import java.util.List;

/**
 * Created by Lukasz Jedrzynski on 08.01.2017.
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

  BookDTO bookToBookDTO(Book book);

  Book bookDTOToBook(BookDTO bookDTO);

  List<Book> bookDTOsToBooks(List<BookDTO> bookDTOS);

  List<BookDTO> booksToBookDTOs(List<Book> books);
}
