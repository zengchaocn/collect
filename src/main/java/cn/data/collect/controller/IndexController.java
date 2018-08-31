package cn.data.collect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        //return "redirect:/task/taskList";
        return "index";
    }

    @RequestMapping(value = "/chooseCoordinate")
    public String chooseCoordinate() {
        return "chooseCoordinate";
    }
}
