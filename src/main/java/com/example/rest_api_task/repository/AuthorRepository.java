package com.example.rest_api_task.repository;

import com.example.rest_api_task.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}