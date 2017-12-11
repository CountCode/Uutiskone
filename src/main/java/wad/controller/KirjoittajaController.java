package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Account;
import wad.domain.Kirjoittaja;
import wad.repository.AccountRepository;
import wad.repository.KategoriaRepository;
import wad.repository.KirjoittajaRepository;
import wad.repository.UutinenRepository;
import wad.service.KirjoittajaService;


@Controller
public class KirjoittajaController {
        
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;

    @Autowired
    private KirjoittajaService kirjoittajaService;    
    
    @Autowired
    private KategoriaRepository kategoriaRepository;
    
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @Autowired
    private AccountRepository accountRepository;    
 
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
    public String addKirjoittaja(Model model, @RequestParam String nimi, 
                                                @RequestParam String username, 
                                                @RequestParam String salasana) {
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.setNimi(nimi);
        
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(salasana);
        account = accountRepository.save(account);
        
        kirjoittaja.setAccount(account);
        kirjoittajaRepository.save(kirjoittaja);
        
        return "redirect:/kirjoittajat";
    }
    
    @DeleteMapping("/kirjoittajat/{id}")
    public String poistaKirjoittaja(@PathVariable Long id, Model model) {
        kirjoittajaService.RemoveKirjoittaja(id);
        return "redirect:/kirjoittajat";
    } 
    
}