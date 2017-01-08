package pl.devone.bookstore.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import pl.devone.bookstore.domain.Book;
import pl.devone.bookstore.service.dto.BookDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-01-08T18:52:01+0100",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setPrice( book.getPrice() );
        bookDTO.setPublisher( book.getPublisher() );
        bookDTO.setPublicationDate( book.getPublicationDate() );

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        book.setPrice( bookDTO.getPrice() );
        book.setPublisher( bookDTO.getPublisher() );
        book.setPublicationDate( bookDTO.getPublicationDate() );

        return book;
    }

    @Override
    public List<Book> bookDTOsToBooks(List<BookDTO> bookDTOS) {
        if ( bookDTOS == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>();
        for ( BookDTO bookDTO : bookDTOS ) {
            list.add( bookDTOToBook( bookDTO ) );
        }

        return list;
    }

    @Override
    public List<BookDTO> booksToBookDTOs(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>();
        for ( Book book : books ) {
            list.add( bookToBookDTO( book ) );
        }

        return list;
    }
}
