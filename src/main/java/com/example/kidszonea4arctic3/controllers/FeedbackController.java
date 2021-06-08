package com.example.kidszonea4arctic3.controllers;

import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.models.Feedback;
import com.example.kidszonea4arctic3.repositories.EmployeeRepository;
import com.example.kidszonea4arctic3.repositories.FeedbackRepository;
import com.example.kidszonea4arctic3.services.EmployeeService;
import com.example.kidszonea4arctic3.services.FeedbackService;
import com.example.kidszonea4arctic3.utilis.SendEmail;
import com.example.kidszonea4arctic3.utilis.SendEmailHTML;
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
@Controller(value = "feedbackController")
@ELBeanName(value = "feedbackController")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    private Feedback feedback = new Feedback();


    @Autowired
    private final FeedbackRepository feedbackRepository;

    @Autowired
    private final SessionController sessionController;

    public FeedbackController(FeedbackRepository feedbackRepository, SessionController sessionController) {
        this.feedbackRepository = feedbackRepository;
        this.sessionController = sessionController;
    }

    @RequestMapping("/Feedbacks")
    public String getFeedbacks(Model model){

        model.addAttribute("Feedbacks",feedbackRepository.findAll());

        return "Feedbacks";
    }

    public String replyToFeedback(Feedback f){
        System.out.println("******* reply feedback controller  ************");
        System.out.println(f.toString());
        f.setStatus("resolved");
        feedbackService.reply(f);
        sessionController.logInEmp();
        SendEmail sendEmail = new SendEmail();
        sendEmail.send(f.getParent().getEmail(),"Feedback "+f.getTitle()+" response",f.getReply());
        return "/pages/childCareCenterDashboard.xhtml?faces-redirect=true";
    }

    public String denyFeedback(Feedback f){
        System.out.println("******* reply feedback controller  ************");
        System.out.println(f.toString());
        f.setStatus("denied");
        feedbackService.reply(f);
        sessionController.logInEmp();
        SendEmail sendEmail = new SendEmail();
        sendEmail.send(f.getParent().getEmail(),"Feedback "+f.getTitle()+" response","Feedback denied");
        return "/pages/childCareCenterDashboard.xhtml?faces-redirect=true";
    }

}
