package com.springproject.springfinaldemo.user;

import com.springproject.springfinaldemo.dao.UsersRepository;
import com.springproject.springfinaldemo.dto.UserDto;
import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.entity.User;
import com.springproject.springfinaldemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class UserTests {

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setName("abhiram");
        user.setEmail("uuv@gmail.com");
        user.setRole("ROLE_STUDENT");
        user.setPassword("{bcrypt}" + "$2a$10$NamytTmy.UZ8EJHop8P3We6zeqB3LG99XQYErzEUzLAXd5PoFfQ4m");
        user.setEnabled(true);
        User tempUser = userRepo.save(user);
        String email=user.getEmail();
        assertEquals(email, tempUser.getEmail());
    }
    @Test
    void TestGetAllUsers()
    {
        List<User> list = (List<User>) userRepo.findAll();
        List<User> savedList = list;
        User user = new User();
        user.setName("koushik");
        user.setEmail("koushik@gmail.com");
        user.setPassword("{bcrypt}"+"$2a$10$NamytTmy.UZ8EJHop8P3We6zeqB3LG99XQYErzEUzLAXd5PoFfQ4m");
        user.setEnabled(true);
        user.setRole("ROLE_STUDENT");
        userRepo.save(user);
        list = (List<User>) userRepo.findAll();
        assertEquals(list.size(), savedList.size() + 1);
    }
@Test
void TestUserDetails()
{
    User user = new User();
    user.setName("koushik");
    user.setEmail("koush@gmail.com");
    user.setPassword("{bcrypt}"+"$2a$10$NamytTmy.UZ8EJHop8P3We6zeqB3LG99XQYErzEUzLAXd5PoFfQ4m");
    user.setEnabled(true);
    user.setRole("ROLE_STUDENT");
    User theUser=userRepo.save(user);
    String email=user.getEmail();
    String name=user.getName();
    String role=user.getRole();
    String password=user.getPassword();
    assertEquals(email, theUser.getEmail());
    assertEquals(name,theUser.getName());
    assertEquals(role,theUser.getRole());
    assertEquals(password,theUser.getPassword());
}
    @Test
    void TestUserDto()
    {
        UserDto userDto = new UserDto();
        userDto.setName("Arvind");
        userDto.setEmail("Arvind@gmail.com");
        userDto.setPassword("{bcrypt}"+"$2a$10$NamytTmy.UZ8EJHop8P3We6zeqB3LG99XQYErzEUzLAXd5PoFfQ4m");
        userDto.setRole("ROLE_STUDENT");
        userDto.setEnabled(true);
        User user = modelMapper.map(userDto, User.class);
        User tempUser =userRepo.save(user);
        UserDto userDto1 = modelMapper.map(tempUser, UserDto.class);
        String email=user.getEmail();
        String name=user.getName();
        String role=user.getRole();
        String password=user.getPassword();
        assertEquals(email, userDto1.getEmail());
        assertEquals(name,userDto1.getName());
        assertEquals(role,userDto1.getRole());
        assertEquals(password,userDto1.getPassword());
    }
    @Test
    void TestUserConstructor()
    {
        User user = new User("raju","raju@gmail.com",
                "{bcrypt}"+"$2a$10$NamytTmy.UZ8EJHop8P3We6zeqB3LG99XQYErzEUzLAXd5PoFfQ4m","ROLE_STUDENT",true);
        String email = "raju@gmail.com";
        assertEquals(email,user.getEmail());
    }
    @Test
    void TestAddCollege()
    {
        User user = new User();
        College college = new College("MGIT","HYD");
        user.addCollege(college);
        List<College> array =user.getCollegeArray();
        assertEquals(college, array.get(array.size() - 1));
    }
    @Test
    void TestUserSetCollegeArray()
    {
        User user = new User();
        College college1 = new College("anurag","hyd");
        College college2 = new College("geetham","hyd");
        List<College>list = new ArrayList<College>(Arrays.asList(college1,college2));
        user.setCollegeArray(list);
        assertEquals(list,user.getCollegeArray());
    }
    @Test
    void TestGetUserById()
    {
         int id =15;
         String name="sainikhil";
         User user =userService.findByUserId(15);
         assertEquals(name,user.getName());
    }

}
