package com.example.rest_api_task.repository;

import com.example.rest_api_task.entity.Author;
import com.example.rest_api_task.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.author.id = ?1")
     Book getBookByAuthorId(@Param("id") Long id);
}