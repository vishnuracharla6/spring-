package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.dao.UsersRepository;
import com.springproject.springfinaldemo.entity.User;

import com.springproject.springfinaldemo.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInterface {

     @Autowired
    private UsersRepository usersRepository;


    public UserServiceImpl(UsersRepository theUsersRepository)
    {
        usersRepository = theUsersRepository;
    }



    @Override
    public User findByUserId(int theId){
        Optional<User>result = usersRepository.findById(theId);

        User theUser=null;
        if(result.isPresent()){
            theUser= result.get();
        }
        else {
            throw new IdNotFoundException("Did not find id - " + theId);
        }
        return theUser;
    }
    @Override
    public void saveUser(User theUser) {
        usersRepository.save(theUser);
    }



    @Override
    public User getUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = null;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();


            List<User> users = usersRepository.findAll();
            for (User user : users) {
                if (user.getName().equals(username)) {
                    theUser=user;
                }
            }
        }
        return theUser;
    }

}
