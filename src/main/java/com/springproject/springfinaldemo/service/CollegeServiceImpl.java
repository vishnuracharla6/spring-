package com.springproject.springfinaldemo.service;

import com.springproject.springfinaldemo.dao.CollegeRepository;
import com.springproject.springfinaldemo.entity.College;
import com.springproject.springfinaldemo.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeServiceImpl implements CollegeInterface {

    @Autowired
    private CollegeRepository collegeRepository;


    public CollegeServiceImpl(CollegeRepository theCollegeRepository)
    {
        collegeRepository = theCollegeRepository;
    }
    @Override
    public List<College> findAllColleges()
    {
        return collegeRepository.findAll();
    }

    @Override
    public College findByCollegeId(int theId){
        Optional<College> result = collegeRepository.findById(theId);
        College theCollege = null;

        if (result.isPresent()) {
            theCollege = result.get();
        }
        else {
            throw new IdNotFoundException("Did not find employee id - "+theId);
        }
        return theCollege;
    }
     @Override
     public void saveCollege(College theCollege) {
        collegeRepository.save(theCollege);
    }

      @Override
      public void deleteByCollegeId(int theId) {
        Optional<College> result = collegeRepository.findById(theId);
         if (result.isPresent()) {
            collegeRepository.deleteById(theId);
          }
         else {
            throw new IdNotFoundException("Did not find employee id - " + theId);
          }

    }

}
