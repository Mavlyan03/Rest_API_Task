package com.example.rest_api_task.service;

import com.example.rest_api_task.dto.AuthorRequest;
import com.example.rest_api_task.dto.AuthorResponse;
import com.example.rest_api_task.dto.BookRequest;
import com.example.rest_api_task.dto.BookResponse;
import com.example.rest_api_task.entity.Author;
import com.example.rest_api_task.entity.Book;
import com.example.rest_api_task.exception.NotFoundException;
import com.example.rest_api_task.repository.AuthorRepository;
import com.example.rest_api_task.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    private final AuthorRepository authorRepository;

    public BookResponse save(BookRequest request) {
        Book book = new Book();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        Author author = authorRepository.findById(request.getAuthorId()).get();
        author.addBook(book);
        book.setAuthor(author);
        Book book1 = repository.save(book);
        int year = Integer.parseInt(String.valueOf(Period.between(request.getPublicationDate(),LocalDate.now()).getYears()));
        return new BookResponse(book1.getId(),book1.getName(),book1.getPublicationDate(),book1.getDescription(),
                book1.getGenre(),book1.getPublisher(),year);
    }

    public BookResponse getById(Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Book with id = %s not found", id)));
        int year = Integer.parseInt(String.valueOf(Period.between(book.getPublicationDate(), LocalDate.now()).getYears()));
        return new BookResponse(book.getId(),book.getName(),book.getPublicationDate(),book.getDescription(),
                book.getGenre(),book.getPublisher(),year);
    }

    public BookResponse getBookByAuthorId(Long id) {
        Book book;
        Author author = new Author();
        if(id == author.getId()) {
             book = repository.getBookByAuthorId(id);
        } else {
            throw new NotFoundException(String.format("Book with author id = %s not found",id));
        }
        int year = Integer.parseInt(String.valueOf(Period.between(book.getPublicationDate(),LocalDate.now()).getYears()));
        return new BookResponse(book.getId(),book.getName(),book.getPublicationDate(),book.getDescription(),
                book.getGenre(),book.getPublisher(),year);
    }


    public BookResponse update(Long id, BookRequest request) {
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Book with id = %s not found", id)));
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        Author author = authorRepository.findById(request.getAuthorId()).get();
        author.addBook(book);
        book.setAuthor(author);
        int year = Integer.parseInt(String.valueOf(Period.between(request.getPublicationDate(),LocalDate.now()).getYears()));
        repository.save(book);
        return new BookResponse(book.getId(),book.getName(),book.getPublicationDate(),book.getDescription(),
                book.getGenre(),book.getPublisher(),year);
    }


    public  BookResponse deleteById(Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Book with id = %s not found", id)));
        book.setAuthor(null);
        repository.delete(book);
        Book book1 = new Book();
        book1.setId(book.getId());
        book1.setName(book.getName());
        book1.setPublisher(book.getPublisher());
        book1.setGenre(book.getGenre());
        book1.setDescription(book.getDescription());
        book1.setPublicationDate(book.getPublicationDate());
        book1.setBookYear(book.getBookYear());
        book1.setAuthor(null);
        int year = Integer.parseInt(String.valueOf(Period.between(book.getPublicationDate(),LocalDate.now()).getYears()));
        book1.setBookYear(year);
        return new BookResponse(book.getId(),book.getName(),book.getPublicationDate(),book.getDescription(),
                book.getGenre(),book.getPublisher(),year);
    }

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

}