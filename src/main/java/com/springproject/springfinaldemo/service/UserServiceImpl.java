package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.dao.UsersRepository;
import com.springproject.springfinaldemo.entity.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInterface {


    private UsersRepository usersRepository;


    public UserServiceImpl(UsersRepository theUsersRepository)
    {
        usersRepository = theUsersRepository;
    }
    @Override
    public List<User> findAll(){
        return usersRepository.findAll();
    }

    @Override
    public User findById(int theId){
        Optional<User>result = usersRepository.findById(theId);

        User theUser=null;
        if(result.isPresent()){
            theUser= result.get();
        }
        else {

            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theUser;
    }
    @Override
    public void save(User theUser) {
        usersRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        usersRepository.deleteById(theId);
    }

    @Override
    public User searchUser()
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
