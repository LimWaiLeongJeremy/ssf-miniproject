package vvt2022.miniProject.badWordDetecter.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vvt2022.miniProject.badWordDetecter.model.Detector;
import vvt2022.miniProject.badWordDetecter.model.User;
import vvt2022.miniProject.badWordDetecter.service.DetectorRedis;

@Controller
@RequestMapping (path = "/result")
public class DetectorController {
    private static final Logger logger = LoggerFactory.getLogger(DetectorController.class);

    @Autowired
    DetectorRedis service;
    
    Detector revealResult;

    //From index.html
    @PostMapping (consumes="application/x-www-form-urlencoded")
    public String contentForm(@ModelAttribute User user
                                , Model model) throws IOException {
        String modUsername = user.getUsername().toLowerCase();
        user.setUsername(modUsername);
        Detector result = service.getResult(user);
        result.setUsername(user.getUsername());
        model.addAttribute("detector", result);
        service.save(result, user);
        this.revealResult = result;         
        return "result";
    }

    //From index.html
    @PostMapping ("/search")
    public String searchHistory(@ModelAttribute String username
                                , Model model){
        model.addAttribute("username", username);
        return "searchHistory";
    }

    //From URL
    @GetMapping ("/{username}")
    public String getHistoryRecord (Model model
                            , @PathVariable(value = "username") String username) {
        Optional<List<Detector>> opt = service.findByUser(model, username);
        if (opt.isEmpty()) {
            return "erroruser";
        }
        service.findByUser(model, username);
        return "history";
    }

    //From result.html
    @GetMapping ("/reveal")
    public String revealContent(Model model, Detector detector){
        model.addAttribute("detectorReveal", this.revealResult);
        return "reveal";
    }
}
