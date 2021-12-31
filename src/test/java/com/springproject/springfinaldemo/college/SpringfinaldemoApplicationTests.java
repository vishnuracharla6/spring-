package com.springproject.springfinaldemo.college;

import com.springproject.springfinaldemo.dao.CollegeRepository;
import com.springproject.springfinaldemo.dao.UsersRepository;
import com.springproject.springfinaldemo.dto.CollegeDto;
import com.springproject.springfinaldemo.dto.UserDto;
import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.entity.User;

import com.springproject.springfinaldemo.service.CollegeServiceImpl;
import com.springproject.springfinaldemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringfinaldemoApplicationTests {

    @Autowired
    private CollegeRepository collegeRepo;

    @Autowired
    private CollegeServiceImpl collegeService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testCreateCollege() {
        CollegeDto collegeDto = new CollegeDto();
        collegeDto.setCollegeName("sr enginerr college");
        collegeDto.setAddress("warangal");
        College college = modelMapper.map(collegeDto, College.class);
        College tempCollege = collegeRepo.save(college);
        assertEquals(tempCollege.getCollegeName(), college.getCollegeName());
        assertEquals(tempCollege.getAddress(), college.getAddress());
    }
    @Test
    void testReadAllColleges() {

        List<College> list = (List<College>) collegeService.findAllColleges();
        List<College> savedList = list;
        College college = new College();
        college.setCollegeName("vardhaman engineering college");
        college.setAddress("hyderabad");
        collegeService.saveCollege(college);
        list = (List<College>) collegeService.findAllColleges();
        assertEquals(list.size(), savedList.size() + 1);
    }
    @Test
     void testReadCollege() {

        College college = collegeService.findByCollegeId(11);
        assertEquals("JNTUH", college.getCollegeName());
    }
    @Test
    void testUpdateCollege() {
        College college = new College();
        college.setAddress("hyd");
        college.setCollegeName("Usmania university");
        College tempCollege = collegeRepo.save(college);
        String address = tempCollege.getAddress();
        String collegeName=tempCollege.getCollegeName();
        tempCollege.setAddress("hyderabad");
        collegeService.saveCollege(tempCollege);
        CollegeDto collegeDto = modelMapper.map(tempCollege, CollegeDto.class);
        assertNotEquals(address, collegeDto.getAddress());
        assertEquals(collegeName,collegeDto.getCollegeName());
    }
    @Test
     void testDeleteCollege() {
        College college = new College();
        college.setAddress("hyderabad");
        college.setCollegeName("Vasavi engnerring college");
        College tempCollege = collegeRepo.save(college);
        int id = tempCollege.getId();
        collegeService.deleteByCollegeId(id);
        assertThat(collegeRepo.existsById(id)).isFalse();
    }
    @Test
    void testSaveCollege()
    {
        List<College> list = (List<College>) collegeService.findAllColleges();
        College college = new College("SR","HNK");
        collegeService.saveCollege(college);
        List<College> updatedList = (List<College>) collegeService.findAllColleges();
        assertEquals(list.size()+1, updatedList.size());
    }





}
