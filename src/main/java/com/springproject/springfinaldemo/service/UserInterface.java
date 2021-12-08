package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.entity.User;


import java.util.List;

public interface UserInterface {

     List<User>findAll();

    User findById(int theId);

     void save(User theUser);

     void deleteById(int theId);

     User searchUser();
}
