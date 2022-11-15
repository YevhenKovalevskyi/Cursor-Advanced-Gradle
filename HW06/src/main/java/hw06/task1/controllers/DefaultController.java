package hw06.task1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YevhenKovalevskyi
 */
@RestController
@RequestMapping("/")
public class DefaultController {
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "Hello World! This is index page.";
    }
}
