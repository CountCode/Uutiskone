package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;
import wad.repository.KirjoittajaRepository;

@Service
public class KirjoittajaService {
    
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;

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
    // tarkista uutiset ja poista kaikista niistä joissa on.
    
        kirjoittajaRepository.deleteById(id);        
    }
}