package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import wad.domain.Uutinen;
import wad.repository.UutinenRepository;

@Controller
public class UutinenController {
        
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @GetMapping("/uutiset")
    public String files(Model model) {
        model.addAttribute("uutiset", uutinenRepository.findAll());
        return "uutiset";
    }
   
}
