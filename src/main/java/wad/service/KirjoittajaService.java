package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;
import wad.repository.AccountRepository;
import wad.repository.KirjoittajaRepository;
import wad.repository.UutinenRepository;

@Service
public class KirjoittajaService {
    
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;
    
    @Autowired
    private UutinenRepository uutinenRepository;

    @Autowired
    private AccountRepository accountRepository;    

    public void addUutinen(Long id, Uutinen uutinen) {
        
        Kirjoittaja kirjoittaja = kirjoittajaRepository.getOne(id);
        kirjoittaja.getUutiset().add(uutinen);
        kirjoittajaRepository.save(kirjoittaja);
    }

    public void removeUutinen(Long id, Uutinen uutinen) {
        
        Kirjoittaja kirjoittaja = kirjoittajaRepository.getOne(id);
        kirjoittaja.getUutiset().remove(uutinen);
        kirjoittajaRepository.save(kirjoittaja);
    }    
    
    public void RemoveKirjoittaja(Long id) {
        Kirjoittaja kirjoittaja = kirjoittajaRepository.getOne(id);
        
         for (Uutinen uutinen : kirjoittaja.getUutiset()){
            uutinen.getKirjoittajat().remove(uutinen.getId());
            uutinenRepository.save(uutinen);
        }
         
        Long accountId = kirjoittaja.getAccount().getId();
        kirjoittaja.setAccount(null); ;
        accountRepository.deleteById(accountId);
  
        kirjoittajaRepository.deleteById(id);        
    }
}