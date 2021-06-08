package com.example.kidszonea4arctic3.controllers;

import com.example.kidszonea4arctic3.models.Child;
import com.example.kidszonea4arctic3.repositories.ChildRepository;
import com.example.kidszonea4arctic3.services.ChildService;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.faces.bean.RequestScoped;
import java.util.List;

@Controller(value = "childController")
@ELBeanName(value = "childController")
@RequestScoped
public class ChildController {

    @Autowired
    private final ChildService childService;

    @Autowired
    private final ChildRepository childRepository;

    @Autowired
    private final SessionController sessionController;

    private List<Child> children;

    private Child child = new Child();


    public ChildController(ChildService childService, ChildRepository childRepository, SessionController sessionController) {
        this.childService = childService;
        this.childRepository = childRepository;
        this.sessionController = sessionController;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }


    public List<Child> getchildren(){
        return childRepository.findAll();
    }

    @RequestMapping(value ="/ChildDelted/{id}", method = RequestMethod.GET)
    public String DeleteChildById(@PathVariable Long id){
        Child child = new Child();
        if (childRepository.findById(id).isPresent()) {
            child = childRepository.findById(id).get();
            childRepository.delete(child);
            System.out.println("Child Deleted");
            return "Child deleted";
        }
        else
        {
            System.out.println("Child not found");
            return "Child not found";
        }
    }
    @RequestMapping(value = "/ChildUpdateFNameById/{id}/{fName}", method = RequestMethod.GET)
    public Child updateFnameById(@PathVariable Long id, @PathVariable String fName){
        Child child = new Child();
        if (childRepository.findById(id).isPresent()){
            child = childRepository.findById(id).get();
            child.setfName(fName);
            return childRepository.save(child);
        }
        else
        {
            return child;
        }
    }
    @RequestMapping(value = "/ChildUpdateLNameById/{id}/{lName}", method = RequestMethod.GET)
    public Child updateLnameById(@PathVariable Long id, @PathVariable String lName){
        Child child = new Child();
        if (childRepository.findById(id).isPresent()){
            child = childRepository.findById(id).get();
            child.setlName(lName);
            return childRepository.save(child);
        }
        else
        {
            return child;
        }
    }
    @RequestMapping(value = "/ChildUpdateAgeById/{id}/{age}", method = RequestMethod.GET)
    public Child updateAgeById(@PathVariable Long id, @PathVariable int age){
        Child child = new Child();
        if (childRepository.findById(id).isPresent()){
            child = childRepository.findById(id).get();
            child.setAge(age);
            return childRepository.save(child);
        }
        else
        {
            return child;
        }
    }

    public void addChild(){
        System.out.println("adding child");
        System.out.println(child);
        childService.addChild(child);
        this.child.setId(null);
    }

    public String deleteChild(Child child) {
        childService.deleteChild(child);
        return "/index.xhtml?faces-redirect=true";
    }

    public String updateChild(Long id,String fName,int age,String gender){
        childService.updateChildFName(id,fName);
        childService.updateChildAge(id,age);
        childService.updateChildGender(id,gender);
        return "/index.xhtml?faces-redirect=true";
    }
}
