package wad.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;
import wad.repository.UutinenRepository;
import wad.service.UutinenService;

@Controller
public class UutinenController {
        
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @Autowired
    private UutinenService uutinenService;
    
    @GetMapping("/uutiset")
    public String files(Model model) {
        model.addAttribute("uutiset", uutinenRepository.findAll());
        return ("uutiset");
    }
   
    @GetMapping("/uutiset/{id}")
    public String files(@PathVariable Long id, Model model) {
        Uutinen uutinen = uutinenRepository.getOne(id);
        System.out.println("UUTINEN");
        System.out.println(uutinen.getOtsikko());        
        System.out.println(uutinen.getKirjoittajat().toString());
        model.addAttribute("uutinen", uutinen);
        return ("uutinen");
    }
    
    @PostMapping("/uutiset" )
    public String addUutinen(Model model, @ModelAttribute Uutinen uutinen) {
        uutinen.setJulkaisuaika(LocalDateTime.now());
        uutinenRepository.save(uutinen);
        return "redirect:/hallintapaneeli";
    }
    
    @PostMapping("/uutiset/{uutinenId}/kirjoittajat" )
    public String addKirjoittaja(@PathVariable Long uutinenId, Model model, @RequestParam Long kirjoittajaId) {
        
        uutinenService.LiitaKirjoittaja(uutinenId, kirjoittajaId);
        return "redirect:/hallintapaneeli";
    }   
            
    @PostMapping("/uutiset/{uutinenId}/kategoriat" )
    public String addKategoria(@PathVariable Long uutinenId, Model model, @RequestParam Long kategoriaId) {
        System.out.println("CNTR: Liitä kateg");
        uutinenService.LiitaKategoria(uutinenId, kategoriaId);
        return "redirect:/hallintapaneeli";
    }              
}
