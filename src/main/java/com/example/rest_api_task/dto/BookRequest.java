package com.example.rest_api_task.dto;

import com.example.rest_api_task.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookRequest {
    private String name;
    private LocalDate publicationDate;
    private String description;
    private Genre genre;
    private String publisher;
    private Long authorId;

//(authorId аркылуу авторду таап book ка set кылыныздар)
}
