package com.springproject.springfinaldemo.dto;

import com.springproject.springfinaldemo.entity.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CollegeDto {

    private int id;

    private String collegeName;


    private String address;


    private List<User> users;
}
