package ru.process.thymeleaf.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/")
  String index(Principal principal) {
    //return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    return "processes";
  }

}
