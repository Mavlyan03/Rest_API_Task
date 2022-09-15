package com.example.rest_api_task.dto;

import com.example.rest_api_task.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorResponse {
    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String nationality;
    private Gender gender;
    private int age;

}
