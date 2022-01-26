package com.spring.validation.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class SaveUserForm {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

}
