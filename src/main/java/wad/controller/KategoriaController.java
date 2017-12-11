package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Kategoria;
import wad.repository.KategoriaRepository;
import wad.service.KategoriaService;

@Controller
public class KategoriaController {
        
    @Autowired
    private KategoriaRepository kategoriaRepository;
    
    @Autowired
    private KategoriaService kategoriaService;    
    
    @PostConstruct
    public void init() {
        Kategoria ktg = new Kategoria();
        if (kategoriaRepository.findByNimi("Ohjelmointi").isEmpty()){
            ktg.setNimi("Ohjelmointi");
            kategoriaRepository.save(ktg);        
        }
        ktg = new Kategoria();
        if (kategoriaRepository.findByNimi("Opetus").isEmpty()){
            ktg.setNimi("Opetus");
            kategoriaRepository.save(ktg);        
        }
        ktg = new Kategoria();
        if (kategoriaRepository.findByNimi("Osaaminen").isEmpty()){
            ktg.setNimi("Osaaminen");
            kategoriaRepository.save(ktg);        
        }
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
    
    @DeleteMapping("/kategoriat/{id}")
    public String poistaKategoria(@PathVariable Long id, Model model) {
        kategoriaService.RemoveKategoria(id);
        return "redirect:/kategoriat";
    } 
    
}