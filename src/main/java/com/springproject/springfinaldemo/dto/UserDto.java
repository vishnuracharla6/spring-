package com.springproject.springfinaldemo.dto;

import com.springproject.springfinaldemo.entity.College;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class UserDto {

    private int id;

    private String name;

    private String password;

    private String email;

    private String role;

    private boolean enabled;

    private List<College> collegeArray;


}
