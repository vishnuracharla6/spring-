package com.springproject.springfinaldemo.dao;

import com.springproject.springfinaldemo.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Integer> {
}
