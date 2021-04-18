package com.uem.simple.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String home() {
        return "index";
    }

    // @GetMapping("/provider")
    // public String provider() {
    //     return "provider";
    // }

    //@GetMapping("/employee")
    //public String employee() {
    //    return "employee";
    //}

    //@GetMapping("/budget")
    //public String budget() {
    //    return "budget";
    //}

    // @GetMapping("/product")
    // public String product() {
    //     return "product";
    // }

    @GetMapping("/report")
    public String report() {
        return "report";
    }
    /*
    @GetMapping("/service")
    public String service() {
        return "service";
    }
    */
    @GetMapping("/sale")
    public String sale() {
        return "sale";
    }
}