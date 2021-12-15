package ite.fifthyear.is.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testfrontcontroller {
    @GetMapping(value = "/account")
    public String viewAcountPage() {

        return "Account/Account";
    }
    @GetMapping(value = "/registerfront")
    public String register() {

        return "register";
    }
    @GetMapping(value = "/add")
    public String add() {

        return "Account/AddAcount";
    }
    @GetMapping(value = "/edit")
    public String edit() {

        return "Account/EditAccount";
    }
    @GetMapping(value = "/show")
    public String show() {

        return "Account/show";
    }

}
