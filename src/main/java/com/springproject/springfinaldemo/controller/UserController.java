package com.springproject.springfinaldemo.controller;

import com.springproject.springfinaldemo.dto.CollegeDto;
import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.entity.User;
import com.springproject.springfinaldemo.exception.CollegesnotAvailable;
import com.springproject.springfinaldemo.service.CollegeServiceImpl;
import com.springproject.springfinaldemo.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CollegeServiceImpl collegeServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    public UserController(CollegeServiceImpl theCollegeServiceImpl, UserServiceImpl theUserServiceImpl) {
        this.collegeServiceImpl = theCollegeServiceImpl;
        this.userServiceImpl = theUserServiceImpl;
    }

    @GetMapping("/list")
    public String listColleges(Model theModel) {

        List<CollegeDto> result = collegeServiceImpl.findAllColleges().stream()
                .map(college -> modelMapper.map(college, CollegeDto.class)).collect(Collectors.toList());

        List<CollegeDto> theColleges = null;
        if (!result.isEmpty()) {
            theColleges = result;
        } else {
            throw new CollegesnotAvailable("Colleges are not added ");
        }
        User user = userServiceImpl.getUser();
        theModel.addAttribute("CollegesList", theColleges);
        theModel.addAttribute("user", user);

        return "select-colleges";
    }

    @PostMapping("/save")
    public String addUserColleges(@ModelAttribute("user") User theUser, Model theModel) {

        User user = userServiceImpl.findByUserId(theUser.getId());
        user.setCollegeArray(theUser.getCollegeArray());
        userServiceImpl.saveUser(user);
        List<College> savedColleges = user.getCollegeArray();
        theModel.addAttribute("selectedList", savedColleges);
        if(user.getCollegeArray().isEmpty())
            return "redirect:/user/list";

        return "colleges/selected-colleges";
    }
}