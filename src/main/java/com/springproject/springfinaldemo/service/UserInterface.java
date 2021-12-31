package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.entity.User;


import java.util.List;

public interface UserInterface {

     //List<User>findAllUsers();

    User findByUserId(int theId);

     void saveUser(User theUser);

     //void deleteByUserId(int theId);

     User getUser();
}
