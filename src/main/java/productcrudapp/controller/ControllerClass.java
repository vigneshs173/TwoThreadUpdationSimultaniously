package productcrudapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productcrudapp.Service.ServiceClass;
import productcrudapp.model.Student;

import java.util.List;

@Controller
public class ControllerClass {


    @Autowired
    private ServiceClass serviceClass;
    @GetMapping("/GetDetails")
    public String GetStud()
    {
        return "studentInput";
    }

    @PostMapping("/GetDetails")
    public String PostStud(@ModelAttribute Student student)
    {
        serviceClass.saveDetailsService(student);
        return "redirect:/GetDetails";
    }

    @GetMapping("/list")
    public String GetDetailsController(Model model)
    {
        List<Student> students=serviceClass.GetDetailsService();
        model.addAttribute("students",students);
        return "list";
    }
    @GetMapping("/fetching")
    public String fetchingGet()
    {
        return "fetch";
    }

    @PostMapping("/fetching")
    public String FetchDetailsController(@RequestParam Long id,Model model)
    {
            Student fetchStudent=serviceClass.FetchDetailsService(id);
            model.addAttribute("student",fetchStudent);
            return "fetch";
    }

    @GetMapping("/update")
    public String updateGet()
    {
        return "update";
    }

    @PostMapping("/update")
    public String UpdateController(@ModelAttribute Student student) throws InterruptedException {
        serviceClass.test(student);
        return "update";
    }

}
