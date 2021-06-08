package com.example.kidszonea4arctic3.controllers;

import com.example.kidszonea4arctic3.models.ChildCareCenter;
import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.models.Feedback;
import com.example.kidszonea4arctic3.models.Parent;
import com.example.kidszonea4arctic3.repositories.ParentRepository;
import com.example.kidszonea4arctic3.services.ChildCareCenterService;
import com.example.kidszonea4arctic3.services.EmployeeService;
import com.example.kidszonea4arctic3.services.FeedbackService;
import com.example.kidszonea4arctic3.services.ParentService;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;

@SessionScope
@Controller(value = "sessionController")
@ELBeanName(value = "sessionController")
public class SessionController {
    private Parent parent = new Parent();
    private Employee employee = new Employee();
    private ArrayList<Employee> employeeArrayList = new ArrayList<>();
    private ArrayList<Feedback> feedbacksArrayList = new ArrayList<>();
    private ChildCareCenter childCareCenter = new ChildCareCenter();
    private boolean status=false;
    private String login;
    private String pw;
    private PieChartModel pieChartModelEmployeRoles = new PieChartModel();


    @Autowired
    private ParentService parentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildCareCenterService childCareCenterService;

    @Autowired
    private FeedbackService feedbackService;

    public SessionController() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public ArrayList<Employee> getEmployeeArrayList() {
        return employeeArrayList;
    }

    public void setEmployeeArrayList(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }

    public ArrayList<Feedback> getFeedbacksArrayList() {
        return feedbacksArrayList;
    }

    public void setFeedbacksArrayList(ArrayList<Feedback> feedbacksArrayList) {
        this.feedbacksArrayList = feedbacksArrayList;
    }

    public void addEmploye(Employee employee){
        logInEmp();
        employeeArrayList.add(employee);
    }

    public ChildCareCenter getChildCareCenter() {
        return childCareCenter;
    }

    public void setChildCareCenter(ChildCareCenter childCareCenter) {
        this.childCareCenter = childCareCenter;
    }

    public String logIn() throws NullPointerException {
        System.out.println("fired login with "+login+" "+pw);
        String navigateTo = "null";


        try {
        if (parentService.authenticate(login,pw)==null) {
            navigateTo="/index?faces-redirect=true";
            FacesMessage facesMessage =

                    new FacesMessage("Login Failed: please check your username/password and try again.");

            FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
        } else {
            navigateTo = "/index?faces-redirect=true";
            this.setStatus(true);
            parent=parentService.authenticate(login, pw);
        } } catch (NullPointerException e) {
            System.out.println(e);
        }

        return navigateTo;
    }

//login employee
    public String logInEmp() throws NullPointerException {
        System.out.println("login emp fired login with "+login+" "+pw);
        String navigateTo = "/index?faces-redirect=true";
        System.out.println("here");

        try {
            if (employeeService.authenticate(login, pw)==null) {
                System.out.println("here1");
                navigateTo="/index?faces-redirect=true";
                FacesMessage facesMessage =

                        new FacesMessage("Login Failed: please check your username/password and try again.");

                FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
            } else {
                System.out.println("here2");
                if(employeeService.authenticate(login, pw).getRole().equals("Director")){
                    System.out.println("here3");
                    employee=employeeService.authenticate(login, pw);
                    childCareCenter = childCareCenterService.getChildCareCenterById(employee.getCcc().getId());
                    employeeArrayList = employeeService.getEmployeeListByCcc(childCareCenter) ;
                    feedbacksArrayList =feedbackService.getFeedbacksByCcc(childCareCenter);
                    System.out.println("si       iiiiii        iii    iiize"+feedbacksArrayList.size());
                    int nbRole1 =0;
                    int nbRole2 =0;
                    int nbRole3 =0;
                    for (Employee emp:employeeArrayList) {
                        switch (emp.getRole()){
                            case "role 1":
                                nbRole1++;
                                break;
                            case "role 2":
                                nbRole2++;
                                break;
                            case "role 3":
                                nbRole3++;
                                break;
                        }

                    }
                    pieChartModelEmployeRoles.set("Role 1",nbRole1);
                    pieChartModelEmployeRoles.set("Role 2",nbRole2);
                    pieChartModelEmployeRoles.set("Role 3",nbRole3);
                    pieChartModelEmployeRoles.setSeriesColors("ffff00,1e90ff,ff1493,cc6666");
                    pieChartModelEmployeRoles.setTitle("Employees number per role");
                    pieChartModelEmployeRoles.setLegendPosition("ne");
                    pieChartModelEmployeRoles.setFill(true);
                    pieChartModelEmployeRoles.setShadow(true);
                    pieChartModelEmployeRoles.setShowDataLabels(true);
                    pieChartModelEmployeRoles.setDiameter(150);
                    System.out.println(employeeArrayList);
                    navigateTo= "/pages/childCareCenterDashboard.jsf?faces-redirect=true" ;
                }

            } } catch (NullPointerException e) {
            System.out.println(e);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return navigateTo;
    }


    public String logOut() {
        System.out.println("fired logout method");
        String navigateTo = "/welcome.xhtml";
        this.setStatus(false);
        this.setParent(null);
        this.setLogin(null);
        this.setPw(null);
        this.setEmployee(null);
        this.setChildCareCenter(null);
        this.setFeedbacksArrayList(null);
        this.setEmployeeArrayList(null);
        this.setPieChartModelEmployeRoles(null);
        return navigateTo;
    }

    public void refresh(){
        if (this.status) {
            Long idUser = this.parent.getId();
            Parent refreshedParent = parentRepository.findById(idUser).get();
            this.setParent(refreshedParent);
        }
    }

    public PieChartModel getPieChartModelEmployeRoles() {
        return pieChartModelEmployeRoles;
    }

    public void setPieChartModelEmployeRoles(PieChartModel pieChartModelEmployeRoles) {
        this.pieChartModelEmployeRoles = pieChartModelEmployeRoles;
    }
}
