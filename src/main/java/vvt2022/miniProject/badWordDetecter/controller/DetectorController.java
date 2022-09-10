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

    @PostMapping
    public String contactForm(@ModelAttribute User user
                            , Model model) throws IOException {
        Detector result = service.getResult(user);
        result.setUsername(user.getUsername());
        logger.info("controller");
        logger.info(result.toString());
        model.addAttribute("detector", result);
        service.save(result, user);

        return "result";
    }

    @GetMapping ("/{username}")
    public String getHistoryRecord (Model model
                            , @PathVariable(value = "username") String username) {
        Optional<List<Detector>> opt = service.findByUser(model, username);
        if (opt.isEmpty()) {
            return "erroruser";
        }
        service.findByUser(model, username);;
        return "history";
    }
    // @GetMapping ("/reveal")
    // public String revealContent(@ModelAttribute Detector detector, Model model){

    // }    


// @RestController
// @RequestMapping (path = "/api")
// public class DetecterRestController {
   
//     @Autowired
//     DetectorRedis service;

//     @PostMapping
//     public String contactForm(@ModelAttribute User user
//                             , Model model) throws IOException {
//         Detector result = service.getResult(user);
//         logger.info("controller");
//         logger.info(result.toString());
//         model.addAttribute("detecter", result);
//         service.save(result, user);

//         return "detecter";
//     }
    
// }
    // @PostMapping("/employees")
    // public Employee saveEMployee(@RequestBody Employee employee){
    //     employeeRepository.saveEmployee(employee);
    //     return employee;
    // }
    // @GetMapping("/employees")
    // public List<Employee> findAll(){
    //     return employeeRepository.findAll();
    // }
    // @GetMapping("/employees/{id}")
    // public Employee findById(@PathVariable("id") Integer id){
    //     return employeeRepository.findById(id);
    // }

    // @PutMapping("/employees")
    // public void update(@RequestBody Employee employee){
    //     employeeRepository.update(employee);

    // }
    // @DeleteMapping("/employees/{id}")
    // public  void delete(@PathVariable("id") Integer id){
    //     employeeRepository.delete(id);
    // }

    
  
}
