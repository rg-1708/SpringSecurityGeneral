package portfolio.rg.springsecuritybook.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCHelloController {

    @GetMapping({"/home", "/", "/index", ""})
    public String home() {
        return "home";
    }

}