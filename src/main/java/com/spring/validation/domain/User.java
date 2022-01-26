package com.spring.validation.domain;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
public class User {

    private Long id;

    @NotEmpty
    private String name;

    @NotNull(message = "필수입니다.")
    @Range(min = 1,max = 100)
    private Integer age;


    @NotEmpty
    @Email
    private String email;

}
