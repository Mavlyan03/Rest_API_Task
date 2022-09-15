package com.example.rest_api_task.api;

import com.example.rest_api_task.dto.AuthorRequest;
import com.example.rest_api_task.dto.AuthorResponse;
import com.example.rest_api_task.entity.Author;
import com.example.rest_api_task.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorAPI {

    private final AuthorService service;

    @PostMapping
    public AuthorResponse save(@RequestBody AuthorRequest request) {
        return service.save(request);
    }

    @GetMapping("{id}")
    public AuthorResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public AuthorResponse update(@PathVariable("id")Long id, @RequestBody AuthorRequest request) {
        return service.update(id,request);
    }

    @DeleteMapping("{id}")
    public AuthorResponse delete(@PathVariable("id")Long id) {
        return service.deleteById(id);
    }

    @GetMapping("all")
    public List<Author> findAll() {
        return service.findAllAuthors();
    }

}