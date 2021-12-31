package com.springproject.springfinaldemo.controller;

import com.springproject.springfinaldemo.dao.CollegeRepository;
import com.springproject.springfinaldemo.service.CollegeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {
    @Autowired
  CollegeServiceImpl collegeServiceImpl;
    @Autowired
    CollegeRepository collegeRepo;
    @Autowired
    LoginController loginController;

    private MockMvc mockMvc;
   @Test
    void testLoginController() {
        String response = loginController.showMyLoginPage();
        Assertions.assertEquals("plain-login", response);
    }
    @Test
    void testHomePage()
    {
        String response = loginController.showUserPage();
        Assertions.assertEquals("home", response);
    }
    @Test
    void testShowAccessDenied()
    {
        String response = loginController.showAccessDenied();
        Assertions.assertEquals("access-denied", response);
    }
//    @Test
//    void testShowRegistrationForm() throws Exception {
//        this.mockMvc.perform(get("/register")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("registration-form"));
//    }

}
