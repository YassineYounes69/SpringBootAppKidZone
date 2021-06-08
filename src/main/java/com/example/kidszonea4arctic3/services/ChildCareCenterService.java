package com.example.kidszonea4arctic3.services;

import com.example.kidszonea4arctic3.models.ChildCareCenter;
import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.models.Parent;
import com.example.kidszonea4arctic3.repositories.ChildCareCenterRepository;
import com.example.kidszonea4arctic3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildCareCenterService {
    @Autowired
    ChildCareCenterRepository childCareCenterRepository;

    public void addChildCareCenter(ChildCareCenter childCareCenter) {
        childCareCenterRepository.save(childCareCenter);
    }
    public ChildCareCenter getChildCareCenterById(Long id) {
        return childCareCenterRepository.getChildCareCenterById(id);
    }

    /* public List<Parent> getAllParents() {
        return employeeRepository.getAllParents();
    }*/


}
