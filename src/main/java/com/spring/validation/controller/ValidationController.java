package com.spring.validation.controller;

import com.spring.validation.domain.SaveUserForm;
import com.spring.validation.domain.User;
import com.spring.validation.domain.UserJoinRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ValidationController {


    private final UserJoinRepository userJoinRepository;


    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("user", new User());
        return "join";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("users", userJoinRepository.getAllUsers());
        return "main";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            log.info("errors {}", bindingResult);
            return "/join";
        }

        userJoinRepository.saveUser(user);


        return "redirect:/main";
    }


    @GetMapping("/change/{id}")
    public String change_Info(User user, Model model, @PathVariable Long id) {
        model.addAttribute("user", userJoinRepository.findById(id));

        return "changeForm";
    }

    @PostMapping("/change/{id}")
    public String change_user(@Validated @ModelAttribute("user") SaveUserForm saveUserForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}",bindingResult);
            return "changeForm";
        }
        userJoinRepository.changeUser(saveUserForm);

        return "redirect:/main";
    }
}
