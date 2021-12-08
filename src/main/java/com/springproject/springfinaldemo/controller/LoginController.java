package com.springproject.springfinaldemo.controller;
import com.springproject.springfinaldemo.entity.User;
import com.springproject.springfinaldemo.service.CollegeServiceImpl;
import com.springproject.springfinaldemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    private CollegeServiceImpl collegeServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    public LoginController(CollegeServiceImpl theCollegeServiceImpl,UserServiceImpl theUserServiceImpl)
    {
        this.collegeServiceImpl=theCollegeServiceImpl;
        this.userServiceImpl=theUserServiceImpl;
    }

    @GetMapping("/showMyLoginPage")
    public String ShowMyLoginPage() {
        return "Plain-login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model theModel) {
        User newUser = new User();
        theModel.addAttribute("newUser", newUser);
        return "registration-form";
    }

    @GetMapping("/")
    public String showUserPage() {
        return "home";
    }

    @PostMapping("/newuser")
    public String processRegister(@Valid @ModelAttribute("newUser") User theUser, BindingResult bindingResult) {


        if (bindingResult.hasErrors())
            return "registration-form";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(theUser.getPassword());
        theUser.setPassword("{bcrypt}" + encodedPassword);
        userServiceImpl.save(theUser);
        return "plain-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
