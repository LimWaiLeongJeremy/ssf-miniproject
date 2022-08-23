package vvt2022.miniProject.badWordDetecter.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vvt2022.miniProject.badWordDetecter.model.Detector;
import vvt2022.miniProject.badWordDetecter.model.User;
import vvt2022.miniProject.badWordDetecter.service.DetectorRedis;

@Controller
@RequestMapping (path = "/detecter")
public class DetecterController {
    private static final Logger logger = LoggerFactory.getLogger(DetecterController.class);

    @Autowired
    DetectorRedis service;

    @PostMapping
    public String contactForm(@ModelAttribute User user
                            , Model model) throws IOException {
        // logger.info(detector.getUsername());                        
        // Detecter d = new Detecter();
        // d.setUsername(detector.getUsername());
        // d.setContent(detector.getContent());
        // Detecter result = service.getResult(user, model);
        Detector result = service.getResult(user);
        logger.info("controller");
        logger.info(result.toString());
        model.addAttribute("detecter", result);

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
