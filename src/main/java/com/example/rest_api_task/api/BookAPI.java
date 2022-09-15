package com.example.rest_api_task.api;

import com.example.rest_api_task.dto.BookRequest;
import com.example.rest_api_task.dto.BookResponse;
import com.example.rest_api_task.entity.Book;
import com.example.rest_api_task.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookAPI {

    private final BookService service;

    @PostMapping
    public BookResponse save(@RequestBody BookRequest request) {
        return service.save(request);
    }


    @GetMapping("{id}")
    public BookResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public BookResponse update(@PathVariable("id")Long id, @RequestBody BookRequest request) {
        return service.update(id,request);
    }

    @DeleteMapping("{id}")
    public BookResponse delete(@PathVariable("id")Long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<Book> findAll() {
        return service.findAllBooks();
    }


    @GetMapping("/getByAuthorId/{id}")
    public BookResponse getBookById(@PathVariable("id") Long id) {
        return service.getBookByAuthorId(id);
    }
}
