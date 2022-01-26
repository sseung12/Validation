package com.spring.validation.domain;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

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
