package ite.fifthyear.is.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {


    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String HomePage(){
        return "user/home.html";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String RegisterPage(){
        return "user/registration.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/changePassword")
    public String changePasswordPage(){return "changePassword.html";}

    @RequestMapping(method = RequestMethod.GET, value = "/loginPublic")
    public String loginPage(){ return "loginPublic.html"; }

}
