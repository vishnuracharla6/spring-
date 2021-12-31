package com.springproject.springfinaldemo.controller;
import com.springproject.springfinaldemo.dto.UserDto;
import com.springproject.springfinaldemo.entity.User;
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
import org.modelmapper.ModelMapper;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

    public LoginController(UserServiceImpl theUserServiceImpl)
    {
        this.userServiceImpl=theUserServiceImpl;
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "plain-login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model theModel) {
        UserDto userDto = new UserDto();
        theModel.addAttribute("newUser", userDto);
        return "registration-form";
    }

    @GetMapping("/")
    public String showUserPage() {
        return "home";
    }

    @PostMapping("/newuser")
    public String processRegister(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult) {


        if (bindingResult.hasErrors())
            return "registration-form";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword("{bcrypt}" + encodedPassword);
        User user = modelMapper.map(userDto, User.class);
        userServiceImpl.saveUser(user);
        return "plain-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
