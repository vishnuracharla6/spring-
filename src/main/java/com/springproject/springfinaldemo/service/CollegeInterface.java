package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.entity.College;


import java.util.List;

public interface CollegeInterface {

    List<College> findAll();

     College findById(int theId);

     void save(College theCollege);

     void deleteById(int theId);
}
