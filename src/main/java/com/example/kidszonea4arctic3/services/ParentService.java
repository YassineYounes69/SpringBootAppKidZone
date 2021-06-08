package com.example.kidszonea4arctic3.services;

import com.example.kidszonea4arctic3.models.Parent;
import com.example.kidszonea4arctic3.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class ParentService {

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    ParentRepository parentRepository;

    public void addParent(Parent parent) throws MessagingException {
        parentRepository.save(parent);
        System.out.println(parent.toString()+" added");
        String mail=parent.getEmail();
        String subject="Kidszone Account Activation";
        String activationLink="<a href=\"http://localhost:8080/pages/parentActivated.jsf?id="+parent.getId()+"\"> Here </a>";
        String text="Dear "+parent.getfName()+ " " + parent.getlName() + ",<br>" + "Please click " +activationLink+" to activate your account.";
        emailService.sendSimpleMessage(mail,subject,text);
        System.out.println("Debug Mail: "+text);
    }

    public List<Parent> getAllParents() {
        return parentRepository.getAllParents();
    }

    public Parent authenticate(String login, String password) {
        return parentRepository.getParentByLoginPw(login,password);
    }
}
