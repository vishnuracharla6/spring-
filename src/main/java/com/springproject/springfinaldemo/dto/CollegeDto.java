package com.springproject.springfinaldemo.dto;

import com.springproject.springfinaldemo.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


import java.util.List;
@Data
public class CollegeDto {

    private int id;

    private String collegeName;


    private String address;


    private List<User> users;
}
