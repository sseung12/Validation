package com.spring.validation.controller;

import com.spring.validation.domain.User;
import com.spring.validation.domain.UserJoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
public class ValidationController {


    UserJoinRepository userJoinRepository;

    public ValidationController(UserJoinRepository userJoinRepository) {
        this.userJoinRepository = userJoinRepository;
    }

    @GetMapping("/join")
    public String joinPage(Model model) {
            model.addAttribute("user",new User());
        return "join";
    }


    @PostMapping("/join")
    public String join(@ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(!StringUtils.hasText(user.getName())){
//            bindingResult.addError(new FieldError("user","name",user.getName(),false,null,null,"이름을 입력해주세"));
            bindingResult.addError(new FieldError("user","name", user.getName(),false,new String[]{"required.user.name"},null,null));
        }
        if (user.getAge()==null || user.getAge() < 0) {
//            bindingResult.addError(new FieldError("user","age",user.getAge(),false,null,null,"나이는 정수여야합니다."));
            bindingResult.addError(new FieldError("user","age",user.getAge(),false,new String[]{"number.user.age"},new Object[]{0,100},null));
        }
        if ( user.getEmail()==null ||!user.getEmail().contains("@")) {
//            bindingResult.addError(new FieldError("user","name",user.getEmail(),false,null,null,"이메일은 @ 를 포함해야 합니다."));
            bindingResult.addError(new FieldError("user","email",user.getEmail(),false,new String[]{"check.user.email"},null,null));
        }

        if (bindingResult.hasErrors()) {
            log.info("errors {}",bindingResult);
            return  "/join";
        }


        userJoinRepository.saveUser(user);


        return "main";
    }
}
