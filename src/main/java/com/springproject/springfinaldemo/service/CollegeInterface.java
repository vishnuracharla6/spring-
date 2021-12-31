package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.entity.College;


import java.util.List;

public interface CollegeInterface {

    List<College> findAllColleges();

     College findByCollegeId(int theId);

     void saveCollege(College theCollege);

     void deleteByCollegeId(int theId);
}
