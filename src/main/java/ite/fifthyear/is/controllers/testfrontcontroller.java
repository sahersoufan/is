package ite.fifthyear.is.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testfrontcontroller {


    @GetMapping(value = "/account")
    public String viewAcountPage() {

        return "Account/Accounts";
    }
    @GetMapping(value = "/registerfront")
    public String register() {

        return "register";
    }

    @GetMapping(value = "/add")
    public String add() {

        return "Account/AddAccount";
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
