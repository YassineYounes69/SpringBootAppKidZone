package com.example.kidszonea4arctic3.services;

import com.example.kidszonea4arctic3.models.ChildCareCenter;
import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void addEmployee(Employee e) {
        employeeRepository.save(e);
    }

    public void editEmployee(Employee e) {
        System.out.println("*******  service edit *******");
        System.out.println(employeeRepository.save(e));
    }

     public ArrayList<Employee> getEmployeeListByCcc(ChildCareCenter ccc) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>(employeeRepository.getEmployeeListByCcc(ccc));

        return employees;
    }
    public void deleteEmployeeById(Employee employee)  {
        employeeRepository.deleteEmployeeById(employee.getId());
    }

    public Employee authenticate(String login, String password) {
        return employeeRepository.getEmployeeByLoginPw(login,password);
    }
}
