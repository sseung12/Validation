package com.spring.validation.domain;

import com.spring.validation.domain.User;
import com.spring.validation.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserJoinRepository {

    private static Map<Long, User> userList;
    private Long sequence =0L;

    public UserJoinRepository() {
        userList = new HashMap<>();
    }

    public void saveUser(User user) {

        user.setId(++sequence);
        userList.put(sequence,user);

    }

    public User findById(Long id) {

        if(!userList.keySet().contains(id)){
            throw new UserNotFoundException(String.format("ID[%s] is not found",id));
        }else{

            return userList.get(id);
        }

    }

    public ArrayList<User> getAllUsers() {
        return  new ArrayList<User>(userList.values());
    }

    public void changeUser(SaveUserForm saveUserForm) {
        User changeUser =findById(saveUserForm.getId());
        changeUser.setName(saveUserForm.getName());
        changeUser.setEmail(saveUserForm.getEmail());

    }
}
