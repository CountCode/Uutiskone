package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Kirjoittaja;
import wad.repository.KirjoittajaRepository;


@Controller
public class KirjoittajaController {
        
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;
    
    @GetMapping("/kirjoittajat")
    public String kirjoittajat(Model model) {
        model.addAttribute("kirjoittajat", kirjoittajaRepository.findAll());

        return "kirjoittajat";
    }
 
    @PostMapping("/kirjoittajat")
    public String addKirjoittaja(Model model, @RequestParam String nimi) {
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.setNimi(nimi);
        
        kirjoittajaRepository.save(kirjoittaja);
        
        return "redirect:/kirjoittajat";
    }
}