package com.example.kidszonea4arctic3.services;

import com.example.kidszonea4arctic3.models.ChildCareCenter;
import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.models.Feedback;
import com.example.kidszonea4arctic3.repositories.EmployeeRepository;
import com.example.kidszonea4arctic3.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    public void addFeedback(Feedback f) {
        feedbackRepository.save(f);
    }
    
    public ArrayList<Feedback> getFeedbacksByCcc(ChildCareCenter ccc) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>(feedbackRepository.getFeedbacksByCcc(ccc));

        return feedbacks;
    }
   public void reply(Feedback r) {
        System.out.println("*******  service reply feedback *******");
        System.out.println(feedbackRepository.save(r));
    }

/*
     public ArrayList<Employee> getEmployeeListByCcc(ChildCareCenter ccc) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>(employeeRepository.getEmployeeListByCcc(ccc));

        return employees;
    }
    public void deleteEmployeeById(Employee employee)  {
        employeeRepository.deleteEmployeeById(employee.getId());
    }

    public Employee authenticate(String login, String password) {
        return employeeRepository.getEmployeeByLoginPw(login,password);
    }*/
}
