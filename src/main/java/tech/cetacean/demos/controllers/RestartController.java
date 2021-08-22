package tech.cetacean.demos.controllers;
import org.springframework.web.bind.annotation.RestController;
import tech.cetacean.demos.app.StaffingApplication;
import org.springframework.web.bind.annotation.PostMapping;


@RestController

public class RestartController {
    
    @PostMapping("/restart")
    public void restart() {
        StaffingApplication.restart();
    } 
}