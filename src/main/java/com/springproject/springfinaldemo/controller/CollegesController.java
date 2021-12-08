package com.springproject.springfinaldemo.controller;


import com.springproject.springfinaldemo.dto.CollegeDto;
import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.service.CollegeServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/colleges")
public class CollegesController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CollegeServiceImpl collegeServiceImpl;

    @GetMapping("/list")
    public String listBooks(Model theModel) {

        List<CollegeDto> theColleges = collegeServiceImpl.findAll().stream()
                .map(College -> modelMapper.map(College, CollegeDto.class)).collect(Collectors.toList());


        theModel.addAttribute("totalColleges", theColleges);

        return "colleges/list-colleges";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("collegeId") int theId,
                                    Model theModel) {


        CollegeDto theCollege = modelMapper.map(collegeServiceImpl.findById(theId), CollegeDto.class);

        theModel.addAttribute("theCollege", theCollege);

        return "colleges/college-form";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        CollegeDto theCollege = new CollegeDto();

        theModel.addAttribute("theCollege", theCollege);

        return "colleges/college-form";
    }

    @PostMapping("/save")
    public String saveCollege(@ModelAttribute("theCollege") CollegeDto theCollege) {


        College college = modelMapper.map(theCollege, College.class);
        collegeServiceImpl.save(college);

        return "redirect:/colleges/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("collegeId") int theId) {

        collegeServiceImpl.deleteById(theId);

        return "redirect:/colleges/list";

    }
}


