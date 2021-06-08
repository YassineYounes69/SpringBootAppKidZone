package com.example.kidszonea4arctic3.services;

import com.example.kidszonea4arctic3.controllers.ChildController;
import com.example.kidszonea4arctic3.controllers.SessionController;
import com.example.kidszonea4arctic3.models.Child;
import com.example.kidszonea4arctic3.models.Parent;
import com.example.kidszonea4arctic3.repositories.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    SessionController sessionController;



    public void addChild(Child child) {
        System.out.println("entering child service");
        child.setParent(sessionController.getParent());
        child.setlName(sessionController.getParent().getlName());
        System.out.println("last updates before persistence");
        childRepository.save(child);
        System.out.println("added this child to db : "+child.toString());

    }

    public void deleteChild(Child child) {
        childRepository.delete(child);
    }

    public void updateChildFName(Long id, String fName) {
        childRepository.updateChildFName(id,fName);
    }

    public void updateChildAge(Long id, int age) {
        childRepository.updateChildAge(id,age);
    }

    public void updateChildGender(Long id, String gender) {
        childRepository.updateChildGender(id,gender);
    }

    public List<Child> getMyChildren(Parent parent){
        return childRepository.getMyChildren(parent);
    }
}
