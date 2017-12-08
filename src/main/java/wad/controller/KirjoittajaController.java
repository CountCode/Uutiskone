package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Kirjoittaja;
import wad.repository.KategoriaRepository;
import wad.repository.KirjoittajaRepository;
import wad.repository.UutinenRepository;


@Controller
public class KirjoittajaController {
        
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;

    @Autowired
    private KategoriaRepository kategoriaRepository;
    
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @GetMapping("/hallintapaneeli")
    public String hallintapaneeli(Model model) {
        model.addAttribute("uutiset", uutinenRepository.findAll());
        model.addAttribute("kirjoittajat", kirjoittajaRepository.findAll());
        model.addAttribute("kategoriat", kategoriaRepository.findAll());
        return "hallintapaneeli";
    }

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