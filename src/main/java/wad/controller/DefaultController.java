package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.domain.Account;
import wad.repository.AccountRepository;

@Controller
public class DefaultController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;
    
    
    @PostConstruct
    public void init() {
        if (accountRepository.findByUsername("user1") != null) {
            return;
        }

        Account user = new Account();
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("user1"));

        accountRepository.save(user);

        user = new Account();
        user.setUsername("user2");
        user.setPassword(passwordEncoder.encode("user2"));

        accountRepository.save(user);
    }
    
    
    @RequestMapping("/")
    public String handleDefault() {
        return "redirect:/uutiset";
    }
}
