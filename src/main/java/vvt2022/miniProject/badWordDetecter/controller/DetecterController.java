package vvt2022.miniProject.badWordDetecter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vvt2022.miniProject.badWordDetecter.model.Detecter;
import vvt2022.miniProject.badWordDetecter.service.DetecterRedis;

@RestController
@RequestMapping (path = "/detecter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DetecterController {
    private static final Logger logger = LoggerFactory.getLogger(DetecterController.class);

    @Autowired
    DetecterRedis service;

    @GetMapping
    public String contactForm(@RequestParam(required = true) String userName, String content, Model model) {
        model.addAttribute("detecter", new Detecter());
        return "detecter";
    }
    // @PostMapping
    // public ResponseEntity<Detecter> createUser (@RequestBody Detecter userInput){
    //     logger.info(userInput.getUsername());

    //     return ResponseEntity.ok(userInput);
    // }
    // @GetMapping
    // public String showUser (Model model) {
    //     model.addAttribute(attributeValue)
    // }
    
}
