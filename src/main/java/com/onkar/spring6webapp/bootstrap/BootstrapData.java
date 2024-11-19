package com.onkar.spring6webapp.bootstrap;


import com.onkar.spring6webapp.domain.Author;
import com.onkar.spring6webapp.domain.Book;
import com.onkar.spring6webapp.repositories.AuthorRepository;
import com.onkar.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //constructor
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author onkar = new Author();
        onkar.setFirstName("Onkar");
        onkar.setLastName("Dhengale");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Designs");
        ddd.setIsbn("123456");

        Author onkarSaved = authorRepository.save(onkar);
        Book dddSaved = bookRepository.save(ddd);

        Author onkar2 = new Author();
        onkar.setFirstName("Onkar2");
        onkar.setLastName("Dhengale2");

        Book ddd2 = new Book();
        ddd2.setTitle("2Domain Driven Designs");
        ddd2.setIsbn("2123456");

        Author onkarSaved2 = authorRepository.save(onkar2);
        Book dddSaved2 = bookRepository.save(ddd2);

        onkarSaved.getBooks().add(dddSaved);
        onkarSaved2.getBooks().add(dddSaved2);

        //persisting the book association
        authorRepository.save(onkarSaved);
        authorRepository.save(onkarSaved2);

        System.out.println("In Bootstrap");
        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());
    }
}