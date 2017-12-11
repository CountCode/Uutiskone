package wad.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.multipart.MultipartFile;
import wad.domain.Account;
import wad.domain.Kirjoittaja;
import wad.domain.KuvaObjekti;
import wad.domain.Uutinen;
import wad.repository.AccountRepository;
import wad.repository.KirjoittajaRepository;
import wad.repository.KuvaRepository;
import wad.repository.UutinenRepository;
import wad.service.UutinenService;

@Controller
public class UutinenController {
        
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @Autowired
    private KuvaRepository kuvaRepository;

    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;
    
    @Autowired
    private AccountRepository accountRepository;    
    
    @Autowired
    private UutinenService uutinenService;    
   
    @PostConstruct
    public void init() {
        Uutinen uut = new Uutinen();
        uut.setOtsikko("Opi ohjelmoimaan");
        uut.setIngressi("Ohjelmoini on kivaa. Varsinkin Web-ohjelmointi. Ja erityisesti Javalla.");
        uut.setJulkaisuaika(LocalDateTime.now());
        uut.setLukumaara(0);
        uut.setTeksti("Web-ohjelmointi Javalla on tosi kivaa. "
                + "Kaikkien pitäisi opetella ohjelmoimaan Web-sovelluksia. "
                + "Niitä voi sitten yllä pitää GitHub:ssa ja julkaista Herokussa."
                + "Vieläkö kun laittaa Travis CI huolehtimaan testauksesta automaattisesti "
                + "saadaan tosi kätevä pipeline ohjelmistotuotantoon.");

        uutinenRepository.save(uut);
        
        uut = new Uutinen();
        uut.setOtsikko("Muuta kivaa");
        uut.setIngressi("Muuta harrastuksia. Liikunta, lukeminen, laulu.");
        uut.setJulkaisuaika(LocalDateTime.now());
        uut.setLukumaara(0);
        uut.setTeksti("Vaikka ohjelmointi onkin tosi kivaa muitakin asioita kannataa tehdä."
                + " Liikunta laittaa veren kiertämään niin että aivotkin saavat happea "
                + "samalla aineenvaihdunta paranee.");

        uutinenRepository.save(uut);
        
    }

    @GetMapping("/uutiset")
    public String uutiset(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "julkaisuaika");
        model.addAttribute("uutiset", uutinenRepository.findAll(pageable));
        return ("uutiset");
    }
   
    @GetMapping("/uutiset/{id}")
    public String uutinen(@PathVariable Long id, Model model) {
        Uutinen uutinen = uutinenRepository.getOne(id);
        
        uutinen.luettu();
        uutinenRepository.save(uutinen);
        model.addAttribute("uutinen", uutinen);      
        return ("uutinen");
    }
    
    @PostMapping("/uutiset" )
    public String addUutinen(Model model, @ModelAttribute Uutinen uutinen) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(auth.getName());
        Kirjoittaja kirjoittaja = kirjoittajaRepository.findByAccount(account);

        uutinen.addKirjoittaja(kirjoittaja);
        uutinen.setJulkaisuaika(LocalDateTime.now());        
        
        uutinen = uutinenRepository.save(uutinen);
        kirjoittaja.addUutinen(uutinen);
        kirjoittajaRepository.save(kirjoittaja);
        
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

    @PostMapping("/uutiset/{uutinenId}/kuva")
    public String addKuva(@PathVariable Long uutinenId, Model model, @RequestParam("kuvaFile") MultipartFile kuvaFile) throws IOException {
        System.out.println("POST KUVA");
        if (!kuvaFile.getContentType().contains("image/")) {
            System.out.println("NOT KUVA");
            return "redirect:/hallintapaneeli";
        }
        System.out.println("KUVA");
        KuvaObjekti kuvaObjekti = new KuvaObjekti();
        kuvaObjekti.setKuva(kuvaFile.getBytes());
        Uutinen uutinen = uutinenRepository.getOne(uutinenId);
 
        kuvaRepository.save(kuvaObjekti);         
        
        uutinen.setKuvaObjektiId(kuvaObjekti.getId());
        System.out.println("KuvaobjektiId "+kuvaObjekti.getId());
        uutinenRepository.save(uutinen);

        return "redirect:/hallintapaneeli";
    }
    
    @DeleteMapping("/uutiset/{id}")
    public String poistaUutinen(@PathVariable Long id, Model model) {
        Uutinen uutinen = uutinenRepository.getOne(id);
   
        uutinenService.poistaUutinen(uutinen);
        
        return "redirect:/hallintapaneeli";
    }    
}