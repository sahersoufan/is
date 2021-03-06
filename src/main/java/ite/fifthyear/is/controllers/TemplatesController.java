package ite.fifthyear.is.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TemplatesController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String homePage(){
        return "homePage.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/loginPublic")
    public String loginPage(){
        return "loginPublic.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String userPage(){
        return "user.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/changePassword")
    public String changePasswordPage(){return "changePassword.html";}


}
