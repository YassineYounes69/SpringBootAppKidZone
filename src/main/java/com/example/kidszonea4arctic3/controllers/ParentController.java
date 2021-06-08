package com.example.kidszonea4arctic3.controllers;

import com.example.kidszonea4arctic3.models.Child;
import com.example.kidszonea4arctic3.models.Parent;
import com.example.kidszonea4arctic3.repositories.ParentRepository;
import com.example.kidszonea4arctic3.services.ChildService;
import com.example.kidszonea4arctic3.services.ParentService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import net.bootsfaces.utils.FacesMessages;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SessionScoped
@Controller(value = "parentController")
@ELBeanName(value = "parentController")
public class ParentController {

    @Autowired
    ParentService parentService;

    @Autowired
    ChildService childService;

    private List<Parent> parents;

    private Parent parent = new Parent();

    @Autowired
    private final ParentRepository parentRepository;

    @Autowired
    private final SessionController sessionController;

    public ParentController(ParentRepository parentRepository, SessionController sessionController) {
        this.parentRepository = parentRepository;
        this.sessionController = sessionController;
    }

    @RequestMapping(value = "/Parents", method = RequestMethod.GET)
    public Iterable<Parent> getParentsA(){
        return parentRepository.findAll();
        // model.addAttribute("Parents",parentRepository.findAll());
        //return "Parents";
    }

    public void addParent() throws MessagingException {
        System.out.println("parent add method fired");
        parentService.addParent(parent);
    }

    public List<Parent> getParents() {
        parents = parentService.getAllParents();
        return parents;
    }

    @RequestMapping(value ="/ParentDelete/{id}", method = RequestMethod.GET)
    public String deleteParentById(@PathVariable Long id){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()) {
            parent = parentRepository.findById(id).get();
            parentRepository.delete(parent);
            System.out.println("Parent Deleted");
            return "parent deleted";
        }
        else
        {
            System.out.println("Parent not found");
            return "parent not found";
        }
    }

    public String updateParentFName(Long id, String fName){
        System.out.println("updating parent :"+fName);
        try {
            parentRepository.updateParentFullName(id,fName);
            sessionController.refresh();
        } catch (Exception exc) {
            System.out.println(exc);
            return null;
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public String updateParentLName(Long id, String lName){
        System.out.println("updating parent :"+lName);
        try {
            parentRepository.updateParentLName(id,lName);
            sessionController.refresh();
        } catch (Exception exc) {
            System.out.println(exc);
            return null;
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public String updateParentEmail(Long id, String email) {
        System.out.println("updating parent :"+ email);
        try {
            parentRepository.updateParentEmail(id,email);
            sessionController.refresh();
        } catch (Exception exc) {
            System.out.println(exc);
            return null;
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public String updateParentPTel(Long id,int pTel) {
        System.out.println("updating parent :"+ pTel);
        try {
            parentRepository.updateParentPTel(id,pTel);
            sessionController.refresh();
        } catch (Exception exc) {
            System.out.println(exc);
            return null;
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public String updateParentpw(Long id,String pw) {
        System.out.println("updating parent :"+ pw + " id : "+id);
        try {
            parentRepository.updateParentPw(id,pw);
            System.out.println("parent repo update pw");
            sessionController.refresh();
            System.out.println("refreshed");
        } catch (Exception exc) {
            System.out.println(exc);
            System.out.println("Exception ins updateParentPw");
        }

        return "/index.xhtml?faces-redirect=true";
    }

    /*@RequestMapping(value = "/ParentUpdateById}", method = RequestMethod.GET)
    public List<Object> updateParentById(@RequestParam(value = "parentDTO") String parentDTO) throws JsonProcessingException {
        final Parent parent = new ObjectMapper().readValue(parentDTO,Parent.class);
        return Arrays.asList(
                parent.getId(),
                parent.getlName()
                );
        //System.out.println(parent);
        *//*if (parentRepository.findById(parent.getId()).isPresent()) {
            System.out.println("Parent Updated");
            return parentRepository.save(parent);
        }
        else
        {
            System.out.println("Parent not found");
            return parent;
        }*//*
    }*/

    public String loadParent (Long parentId){
        System.out.println("loading parent with id : "+parentId);
        try{
            Parent theParent = parentRepository.findById(parentId).get();
            System.out.println("got parent : "+theParent.toString());
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("parent",theParent);
            System.out.println("printing request map :");
            System.out.println(requestMap.get("parent").toString());
        } catch (Exception exc) {
            System.out.println("error");
            System.out.println(exc);
            return null;
        }
        return "/pages/modifyParent.xhtml";
    }

    public String loadAddChildren(){
        return "/pages/addChild.xhtml";
    }

    public String loadChildren(){
        return "/pages/childManager.xhtml";
    }

    public String loadSearchCcc(){
        return "/pages/cccSearch.xhtml";
    }

    public List<Child> loadMyChildren(){
        return childService.getMyChildren(sessionController.getParent());
    }

    @RequestMapping(value = "/ParentUpdateLastNameById/{id}/{LastName}/{FirstName}", method = RequestMethod.GET)
    public Parent updateFullNameById(@PathVariable Long id,@PathVariable String LastName,@PathVariable String FirstName){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setlName(LastName);
            parent.setfName(FirstName);
            return parentRepository.save(parent);
        }
        else
        {
            return parent;
        }
    }

    @RequestMapping(value = "/ParentUpdateFirstNameById/{id}/{FirstName}", method = RequestMethod.GET)
    public Parent updateFirstNameById(@PathVariable Long id,@PathVariable String FirstName){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setfName(FirstName);
            return parentRepository.save(parent);
        }
        else
        {
            return parent;
        }
    }

    @RequestMapping(value = "/ParentUpdatePwById/{id}/{Pw}", method = RequestMethod.GET)
    public Parent updateLastPwById(@PathVariable Long id,@PathVariable String Pw){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setPw(Pw);
            return parentRepository.save(parent);
        }
        else
        {
            return parent;
        }
    }

    @RequestMapping(value = "/ParentUpdateEmailById/{id}/{Email}", method = RequestMethod.GET)
    public Parent updateLastEmailById(@PathVariable Long id,@PathVariable String Email){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setEmail(Email);
            return parentRepository.save(parent);
        }
        else
        {
            return parent;
        }
    }

    public void parentActivate(@PathVariable Long id){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setAccStatus(true);
            parentRepository.save(parent);

        }
        else
            System.out.println("parent not found");

    }

    @RequestMapping(value = "/ParentDeactivate/{id}", method = RequestMethod.GET)
    public Parent parentDeactivate(@PathVariable Long id){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setAccStatus(false);
            return parentRepository.save(parent);
        }
        else
        {
            return parent;
        }
    }

    @RequestMapping(value = "/ParentUpdatePNById/{id}/{pn}", method = RequestMethod.GET)
    public Parent updatePNById(@PathVariable Long id,@PathVariable int pn){
        Parent parent = new Parent();
        if (parentRepository.findById(id).isPresent()){
            parent = parentRepository.findById(id).get();
            parent.setpTel(pn);
            return parentRepository.save(parent);
        }
        else
        {
            return parent;
        }
    }

    @RequestMapping(value="/updateWholeParent", method = RequestMethod.GET)
    @ResponseBody
    public Parent modifyWholeUser(Parent parent){
        Parent newParent = parentRepository.findById(parent.getId()).get();

        return parentRepository.save(parent);
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String resetPassword(String mail) throws NullPointerException{

        String navigateTo = "null";
        try {
            if (parentRepository.findById(parentRepository.getParentByEmail(mail).getId()).isPresent()) {
                navigateTo = "/index?faces-redirect=true";
                Parent parent = parentRepository.findById(parentRepository.getParentByEmail(mail).getId()).get();
                Long id = parentRepository.getParentByEmail(mail).getId();
                int leftLimit = 97; // letter 'a'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 10;
                Random random = new Random();

                String generatedString = random.ints(leftLimit, rightLimit + 1)
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                System.out.println(generatedString);
                updateParentpw(id, generatedString);
                final String ACCOUNT_SID = "ACa442197644852a343b868bd140ac41d2";
                final String AUTH_ID = "599c51cd25a93d1db526d58ab47a811e";
                String sms = "Your new password is : " + generatedString;
                Twilio.init(ACCOUNT_SID, AUTH_ID);
                Message.creator(new PhoneNumber("216"+parent.getpTel()), new PhoneNumber("12673949255"),
                        sms).create();
                FacesMessages.error("pwResetForm:pwResetFailedGrowl", "New password sent to your phone number", "password reset success");
            } else {
                navigateTo = "/index?faces-redirect=true";

            }
        } catch (Exception e) {
            System.out.println("null pointeur");
            navigateTo = "/index?faces-redirect=true";
            FacesMessages.error("pwResetForm:pwResetFailedGrowl", "No account with specified email found.", "mail error");
        }
        return navigateTo;
    }



}
