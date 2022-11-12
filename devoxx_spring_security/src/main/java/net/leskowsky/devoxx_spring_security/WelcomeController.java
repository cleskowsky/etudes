package net.leskowsky.devoxx_spring_security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }
}
