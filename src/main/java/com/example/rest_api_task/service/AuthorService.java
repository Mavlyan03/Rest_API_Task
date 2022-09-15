package com.example.rest_api_task.service;

import com.example.rest_api_task.dto.AuthorRequest;
import com.example.rest_api_task.dto.AuthorResponse;
import com.example.rest_api_task.entity.Author;
import com.example.rest_api_task.exception.NotFoundException;
import com.example.rest_api_task.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorResponse save(AuthorRequest request) {
        Author author = new Author();
        String[] fullName = {request.getFirstName(), request.getLastName()};
        author.setFullName(fullName[0] + " " + fullName[1]);
        int index = author.getFullName().lastIndexOf(' ');
        String firstName = author.getFullName().substring(0, index);
        String lastName = author.getFullName().substring(index + 1);
        author.setNationality(request.getNationality());
        author.setGender(request.getGender());
        author.setDateOfBirth(request.getDateOfBirth());
        Author author1 = repository.save(author);
        int age = Integer.parseInt(String.valueOf(Period.between(request.getDateOfBirth(), LocalDate.now()).getYears()));
        return new AuthorResponse(author1.getId(),
                firstName, lastName,
                author1.getFullName(),
                author1.getNationality(), author1.getGender(), age);
    }

    public AuthorResponse getById(Long id) {
        Author author = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Author with id = %s not found", id)));
        int age = Integer.parseInt(String.valueOf(Period.between(author.getDateOfBirth(), LocalDate.now()).getYears()));
        int index = author.getFullName().lastIndexOf(' ');
        String firstName = author.getFullName().substring(0, index);
        String lastName = author.getFullName().substring(index + 1);
        return new AuthorResponse(author.getId(), author.getFullName(),
                firstName, lastName, author.getNationality(), author.getGender(), age);
    }


    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author1 = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Author with id = %s not found", id)));
        String[] fullName = {request.getFirstName(), request.getLastName()};
        author1.setFullName(fullName[0] + " " + fullName[1]);
        int index = author1.getFullName().lastIndexOf(" ");
        String firstName = author1.getFullName().substring(0, index);
        String lastName = author1.getFullName().substring(index + 1);
        author1.setGender(request.getGender());
        author1.setNationality(request.getNationality());
        author1.setDateOfBirth(request.getDateOfBirth());
        int age = Integer.parseInt(String.valueOf(Period.between(request.getDateOfBirth(), LocalDate.now()).getYears()));
        repository.save(author1);
        return new AuthorResponse(author1.getId(), author1.getFullName(),
                firstName, lastName, author1.getNationality(), author1.getGender(), age);
    }


    public AuthorResponse deleteById(Long id) {
        Author author = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Author with id = %s not found", id)));
        repository.delete(author);
        Author author1 = new Author();
        author1.setId(author.getId());
        author1.setFullName(author.getFullName());
        int index = author.getFullName().lastIndexOf(" ");
        String firstName = author.getFullName().substring(0,index);
        String lastName = author.getFullName().substring(index + 1);
        author1.setNationality(author.getNationality());
        author1.setGender(author.getGender());
        author1.setDateOfBirth(author.getDateOfBirth());
        int age = Integer.parseInt(String.valueOf(Period.between(author.getDateOfBirth(), LocalDate.now()).getYears()));
        return new AuthorResponse(author1.getId(), author1.getFullName(),
                firstName, lastName, author1.getNationality(), author1.getGender(), age);
    }

    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

}
