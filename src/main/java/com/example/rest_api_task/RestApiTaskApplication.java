package com.example.rest_api_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiTaskApplication.class, args);
    }

//    Entities:
//Book :
//-String name,
//-String description,
//-int bookYear,
//-String publisher,
//-LocalDate publicationDate,
//-Genre genre (enum)
//@ManyToOne
//Author author,
//**********************************************************************
//Author:
//-String fullName,
//-String nationality,
//-Gender gender,
//-LocalDate dateOfBirth
//@OneToMany
//List<Book>books
//**********************************************************************
//Requests:
//AuthorRequest:
//-firstName,
//-lastName,
//-nationality,
//-Gender gender,
//-LocalDate dateOfBirth
//BookRequest:
//-name,
//-LocalDate publicationDate,
//-description,
//-Genre genre (enum),
//-publisher
//-authorId
//(authorId аркылуу авторду таап book ка set кылыныздар)
//********************************************************************
//Responses:
//BookResponse:
//-id
//-name,
//-LocalDate publicationDate,
//-description,
//-Genre genre (enum),
//-publisher
//-int bookYear
//AuthorResponse:
//-id,
//-fullName,
//-firstName,
//-lastName,
//-nationality,
//-gender,
//-int age
//*********************************************************************
//Author жана Book entity лердин бардык CRUD оперцияларын жасаныздар(save, get, update, delete)
//NotFoundException тузуп ошол катаны карматыныздар

}
