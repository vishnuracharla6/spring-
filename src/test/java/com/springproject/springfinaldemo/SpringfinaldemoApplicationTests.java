package com.springproject.springfinaldemo;

import com.springproject.springfinaldemo.dao.CollegeRepository;
import com.springproject.springfinaldemo.dao.UsersRepository;
import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.entity.User;
import com.springproject.springfinaldemo.service.CollegeInterface;
import com.springproject.springfinaldemo.service.CollegeServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringfinaldemoApplicationTests {

    @Autowired
    private CollegeRepository collegeRepo;



    @Autowired
    private UsersRepository userRepo;

    @Test
    void testCreateCollege() {
        College college = new College();
        college.setCollegeName("sr enginerr college");
        college.setAddress("warangal");
        College tempCollege = collegeRepo.save(college);
        assertEquals(tempCollege.getCollegeName(), college.getCollegeName());
        assertEquals(tempCollege.getAddress(), college.getAddress());
    }

    @Test
    public void testReadAllColleges() {

        List<College> list = (List<College>) collegeRepo.findAll();
        List<College> savedList = list;
        College college = new College();
        college.setCollegeName("vardhaman engineering college");
        college.setAddress("hyderabad");
        collegeRepo.save(college);
        list = (List<College>) collegeRepo.findAll();
        assertEquals(list.size(), savedList.size() + 1);
    }

    @Test
    public void testReadCollege() {
        College college = collegeRepo.findById(3).get();
        assertEquals("KMIT", college.getCollegeName());
    }


    @Test
    public void testUpdateCollege() {
        College college = new College();
        college.setAddress("hyd");
        college.setCollegeName("Usmania university");
        College tempCollege = collegeRepo.save(college);
        String address = tempCollege.getAddress();
        tempCollege.setAddress("hyderabad");
        collegeRepo.save(tempCollege);
        assertNotEquals(address, tempCollege.getAddress());
    }

    @Test

    public void testDeleteCollege() {
        College college = new College();
        college.setAddress("hyderabad");
        college.setCollegeName("Vasavi engnerring college");
        College tempCollege = collegeRepo.save(college);
        int id = tempCollege.getId();
        collegeRepo.deleteById(id);
        assertThat(collegeRepo.existsById(id)).isFalse();

    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setName("abhiram");
        user.setEmail("abhiram@gmail.com");
        user.setRole("ROlE_STUDENT");
        user.setPassword("{bcrypt}" + "$2a$10$NamytTmy.UZ8EJHop8P3We6zeqB3LG99XQYErzEUzLAXd5PoFfQ4m");
        user.setEnabled(true);
        User tempUser = userRepo.save(user);
        assertEquals(user.getEmail(), tempUser.getEmail());
    }
}
