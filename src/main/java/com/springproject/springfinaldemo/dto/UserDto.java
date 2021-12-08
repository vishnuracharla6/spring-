package com.springproject.springfinaldemo.dto;

import com.springproject.springfinaldemo.entity.College;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {

    private int id;

    private String name;

    private String password;

    private String email;

    private String role;

    private boolean enabled;

    private List<College> collegeArray;


}
