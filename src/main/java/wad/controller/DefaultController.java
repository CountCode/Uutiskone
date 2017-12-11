package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.domain.Account;
import wad.domain.Kirjoittaja;
import wad.repository.AccountRepository;
import wad.repository.KirjoittajaRepository;

@Controller
public class DefaultController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;
    
    @PostConstruct
    public void init() {
        if (accountRepository.findByUsername("user1") != null) {
            return;
        }

        Kirjoittaja kirjoittaja1 = new Kirjoittaja();
        kirjoittaja1.setNimi("kirjoittaja1");
        
        Account user = new Account();
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("user1"));
        accountRepository.save(user);

        kirjoittaja1.setAccount(user);
        kirjoittajaRepository.save(kirjoittaja1);
        
        if (accountRepository.findByUsername("user2") != null) {
            return;
        }        

        Kirjoittaja kirjoittaja2 = new Kirjoittaja();
        kirjoittaja2.setNimi("kirjoittaja2");
        
        user = new Account();
        user.setUsername("user2");
        user.setPassword(passwordEncoder.encode("user2"));
        accountRepository.save(user);

        kirjoittaja2.setAccount(user);
        kirjoittajaRepository.save(kirjoittaja2);

    }
    
    
    @RequestMapping("/")
    public String handleDefault() {
        return "redirect:/uutiset";
    }
}
