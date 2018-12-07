package com.rinatprod.controller;

import com.rinatprod.domain.Book;
import com.rinatprod.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    private final BookRepository bookRepository;

    @Autowired
    public GreetingController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/showBooks")
    public String showBooks(Map<String, Object> model) {
        Iterable<Book> books = bookRepository.findAll();

        model.put("books", books);
        return "showBooks";
    }

    @GetMapping("/addBook")
    public String addBook(@RequestParam String name, @RequestParam int pubYear, @RequestParam String desc,
                          @RequestParam double price, Map<String, Object> model) {
        Book book = new Book(name, pubYear, desc, price);
        bookRepository.save(book);

        Iterable<Book> books = bookRepository.findAll();

        model.put("books", books);

        return "showBooks";
    }
}
