package com.springproject.springfinaldemo.controller;

import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.entity.User;
import com.springproject.springfinaldemo.exception.CollegesnotAvailable;
import com.springproject.springfinaldemo.service.CollegeServiceImpl;
import com.springproject.springfinaldemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CollegeServiceImpl collegeServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

        public UserController(CollegeServiceImpl theCollegeServiceImpl,UserServiceImpl theUserServiceImpl)
        {
            this.collegeServiceImpl=theCollegeServiceImpl;
            this.userServiceImpl=theUserServiceImpl;
        }

    @GetMapping("/list")
    public String listColleges(Model theModel) {
        List<College> result = collegeServiceImpl.findAll();
        List<College> theColleges = null;

        if (!result.isEmpty()) {
            theColleges = result;
        }
        else {
            // we didn't find the college
            //System.out.println("Did not find college id - " + theId);
            throw new CollegesnotAvailable("Colleges are not added ");
        }
            User user=userServiceImpl.searchUser();

        theModel.addAttribute("CollegesList", theColleges);
        theModel.addAttribute("user", user);
        //List<College> theColleges = collegeServiceImpl.findAll();
        /*
        System.out.println(theColleges);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            System.out.println(username);

            List<User> users = userServiceImpl.findAll();
            for (User user : users) {
                if (user.getName().equals(username)) {
                    theModel.addAttribute("CollegesList", theColleges);
                    theModel.addAttribute("user", user);
                    break;
                }
            }
        }
       */

        return "select-colleges";

    }


    @PostMapping("/save")
    public String addUserColleges(@ModelAttribute("user") User theUser, Model theModel) {


        System.out.println(theUser.getCollegeArray());
        System.out.println(theUser);
        User user = userServiceImpl.findById(theUser.getId());
        user.setCollegeArray(theUser.getCollegeArray());
        userServiceImpl.save(user);
        List<College> savedColleges = user.getCollegeArray();
        theModel.addAttribute("selectedList", savedColleges);
        return "colleges/selected-colleges";
    }
}