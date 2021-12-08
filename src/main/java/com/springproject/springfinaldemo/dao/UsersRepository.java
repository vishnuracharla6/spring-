package com.springproject.springfinaldemo.dao;

import com.springproject.springfinaldemo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {

}
