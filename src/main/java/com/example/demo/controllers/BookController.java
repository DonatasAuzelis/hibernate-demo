package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String author){

        Book book = new Book();

        book.setAuthor(author);
        book.setTitle(title);
        bookRepo.save(book);

        return "Book has been added!";
    }

    @GetMapping("/allBooks")
    public Iterable<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    @GetMapping("/getByAuthor/{author}")
    public List<Book> getByAuthor(@PathVariable String author){
        return bookRepo.findByAuthor(author);
    }

    @GetMapping("/getByAuthor2/{author}")
    public List<Book> getByAuthor2(@PathVariable String author){
        return bookRepo.findBooksByAuthor(author);
    }

    @GetMapping("/getByTitle/{title}")
    public List<Book> getByTitle(@PathVariable String title){
        return bookRepo.findByTitle(title);
    }

    @GetMapping("/getByBoth/{author}&{title}")
    public List<Book> getByBoth(@PathVariable String author, @PathVariable String title){
        return bookRepo.findByAuthorAndTitle(author, title);
    }

    @PostMapping("/delete/{id}")
    public String deleteByID(@PathVariable Long id){

        bookRepo.deleteById(id);
        return "Book was deleted";
    }

    @PostMapping("update/{id}/{author}")
    public String updateAuthorByID(@PathVariable Long id, @PathVariable String author){

        for (Book b: bookRepo.findAll()){
            if (b.getId() == id) b.setAuthor(author);
            bookRepo.save(b);
        }
        return "Author was updated!";
    }
}
