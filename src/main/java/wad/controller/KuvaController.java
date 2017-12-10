package wad.controller;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.Kategoria;
import wad.domain.KuvaObjekti;
import wad.repository.KategoriaRepository;
import wad.repository.KuvaRepository;

@Controller
public class KuvaController {
        
    @Autowired
    private KuvaRepository kuvaRepository;

    @GetMapping(path="/kuva/{kuvaId}", produces = "image/png")
    @ResponseBody
    public byte[] haeKuva(@PathVariable Long kuvaId) throws IOException {
        
        return kuvaRepository.getOne(kuvaId).getKuva();
}
    
    
    @PostMapping("/kuva")
    public String addKuva(Model model, @RequestParam("kuvaFile") MultipartFile kuvaFile) throws IOException {
        if (!kuvaFile.getContentType().equals("image/image")) {
            return "redirect:/hallintapaneeli";
        }
       
        KuvaObjekti kuvaObjekti = new KuvaObjekti();
        kuvaObjekti.setKuva(kuvaFile.getBytes());;
        
        kuvaRepository.save(kuvaObjekti);
        
        return "redirect:/hallintapaneeli";
    }    
}
