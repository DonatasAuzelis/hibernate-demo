package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByAuthorAndTitle(String author, String title);
    List<Book> findBooksByAuthor(String author);
}
