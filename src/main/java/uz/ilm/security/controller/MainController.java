package uz.ilm.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping
    public String home(){
        return "<h1>Hello</h1>";
    }

    @PreAuthorize("@AccessHandler.hasPermission(T(uz.ilm.security.model.Roles).ADMIN)")
    @GetMapping("/users")
    public String add(){
        return "<h1>Alex</h1><h1>White</h1><h1>John</h1>";
    }
}
