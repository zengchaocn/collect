package cn.data.collect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    @RequestMapping("taskList")
    public String taskList() {
        return "task/taskList";
    }
}
