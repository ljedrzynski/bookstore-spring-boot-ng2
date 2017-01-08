package pl.devone.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.devone.bookstore.domain.Book;

import java.util.List;

/**
 * Created by Lukasz Jedrzynski on 08.01.2017.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
  Book save(Book book);

  Book findOne(Long id);

  List<Book> findAll();

  void delete(Book book);

  boolean exists(Long id);
}
