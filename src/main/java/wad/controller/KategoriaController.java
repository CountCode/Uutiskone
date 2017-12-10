package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Kategoria;
import wad.repository.KategoriaRepository;

@Controller
public class KategoriaController {
        
    @Autowired
    private KategoriaRepository kategoriaRepository;
    
    @PostConstruct
    public void init() {
        Kategoria ktg = new Kategoria();
        ktg.setNimi("Ohjelmointi");
        kategoriaRepository.save(ktg);
        ktg = new Kategoria();
        ktg.setNimi("Opetus");
        kategoriaRepository.save(ktg);        
        ktg = new Kategoria();
        ktg.setNimi("Osaaminen");
        kategoriaRepository.save(ktg);        
    }
    
    
    @GetMapping("/kategoriat")
    public String kategoriat(Model model) {
        model.addAttribute("kategoriat", kategoriaRepository.findAll());
        return "kategoriat";
    }

    @PostMapping("/kategoriat")
    public String addKategoria(Model model, @RequestParam String nimi) {
        Kategoria kategoria = new Kategoria();
        kategoria.setNimi(nimi);
        
        kategoriaRepository.save(kategoria);
        
        return "redirect:/kategoriat";
    }    
}