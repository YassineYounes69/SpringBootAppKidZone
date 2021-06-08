package com.example.kidszonea4arctic3.controllers;

import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.repositories.EmployeeRepository;
import com.example.kidszonea4arctic3.repositories.ParentRepository;
import com.example.kidszonea4arctic3.services.EmployeeService;
import com.example.kidszonea4arctic3.utilis.UserPDFExporter;
import com.lowagie.text.DocumentException;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SessionScoped
@Controller(value = "employeeController")
@ELBeanName(value = "employeeController")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    private Employee employee = new Employee();
    private Employee newEmployee = new Employee();
    private Employee editEmployee = new Employee();
    private String error = "";

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final SessionController sessionController;

    public EmployeeController(EmployeeRepository employeeRepository, SessionController sessionController) {
        this.employeeRepository = employeeRepository;
        this.sessionController = sessionController;
    }

    @RequestMapping("/Employees")
    public String getEmployees(Model model){

        model.addAttribute("Employees",employeeRepository.findAll());

        return "Employees";
    }

    public String addOwner(){
        System.out.println("employee add method fired");
        employee.setRole("Director");
        employeeService.addEmployee(employee);
        System.out.println(employee.toString());
        sessionController.setEmployee(employee);
        return "/pages/addChildCareCenter.xhtml?faces-redirect=true";
    }

    public String editOwner(){
        employeeService.addEmployee(sessionController.getEmployee());
        return "/pages/childCareCenterDashboard.jsf?faces-redirect=true";
    }
    public String addEmploye(){
        System.out.println("employee add method fired");
        newEmployee.setCcc(sessionController.getChildCareCenter());
        employeeService.addEmployee(newEmployee);
        System.out.println(newEmployee.toString());
        sessionController.addEmploye(newEmployee);
        newEmployee = new Employee();
        return "/pages/childCareCenterDashboard.xhtml?faces-redirect=true";
    }

    public String editEmploye(Employee emp){
        /*System.out.println("employee add method fired");
        newEmployee.setCcc(sessionController.getChildCareCenter());
        System.out.println(newEmployee.toString());
        sessionController.addEmploye(newEmployee);*/
        System.out.println("******* Edit employe  ************");
        System.out.println(emp.toString());
        employeeService.editEmployee(emp);
        sessionController.logInEmp();

        return "/pages/childCareCenterDashboard.xhtml?faces-redirect=true";
    }

    public void deleteEmployeById(Employee employee){
        System.out.println("***** Deleting employe *****");
        employeeService.deleteEmployeeById(employee);
        ArrayList<Employee> list = sessionController.getEmployeeArrayList();
        list.remove(employee);
        //sessionController.setEmployeeArrayList(list);
        sessionController.logInEmp();
    }

    public void setEditEmp(Employee e){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  "+e);
        editEmployee = sessionController.getEmployeeArrayList().get(sessionController.getEmployeeArrayList().indexOf(e));
    }


    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees_list_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        ArrayList<Employee> listUsers = sessionController.getEmployeeArrayList();

        UserPDFExporter exporter = new UserPDFExporter(listUsers,sessionController.getChildCareCenter().getName());
        exporter.export(response);

    }
    public String checkAuthetication(){
        System.out.println( "zzzzzzz "+sessionController.getEmployee().toString());
        if(sessionController.getEmployee().getRole()==null||!(sessionController.getEmployee().getRole().equals("Director"))){
            System.out.println("u heeeeeeeeeeeeeeeeeeere !!!!");
            return "/pages/childCareCenterRegister.xhtml?faces-redirect=true";
        }
        else {
            System.out.println("u elsssssssssssssssseeee");
            return null;
        }
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Employee getEditEmployee() {
        return editEmployee;
    }

    public void setEditEmployee(Employee editEmployee) {
        this.editEmployee = editEmployee;
    }
}
