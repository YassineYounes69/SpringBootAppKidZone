package com.example.kidszonea4arctic3.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope("session")
@Controller("kidzoneController")
@ELBeanName("kidzoneController")
@Join(
        path = "/index",
        to = "/welcome.jsf"
)
public class KidzoneController {
    @RequestMapping(value = { "/welcome"})
    public String index() {
        return "/welcome.xhtml";
    }

    @RequestMapping(value = {"/registerParent"})
    public String registerParent() {
        return "/pages/parentRegister.xhtml";
    }
}
