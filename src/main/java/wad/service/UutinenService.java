package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Kategoria;
import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;
import wad.repository.UutinenRepository;

@Service
public class UutinenService {
    
    @Autowired
    private UutinenRepository uutinenRepository;

    public void addKirjoittaja(Long id, Kirjoittaja kirjoittaja) {
        
        Uutinen uutinen = uutinenRepository.getOne(id);
        uutinen.getKirjoittajat().add(kirjoittaja);
        uutinenRepository.save(uutinen);
    }

    public void removeKirjoittaja(Long id, Kirjoittaja kirjoittaja) {
        
        Uutinen uutinen = uutinenRepository.getOne(id);
        uutinen.getKirjoittajat().remove(kirjoittaja);
        uutinenRepository.save(uutinen);
    }    

    public void addKategoria(Long id, Kategoria kategoria) {
        
        Uutinen uutinen = uutinenRepository.getOne(id);
        uutinen.getKategoriat().add(kategoria);
        uutinenRepository.save(uutinen);
    }

    public void removeKirjoittaja(Long id, Kategoria kategoria) {
        
        Uutinen uutinen = uutinenRepository.getOne(id);
        uutinen.getKategoriat().remove(kategoria);
        uutinenRepository.save(uutinen);
    } 
    
    public void RemoveUutinen(Long id) {
        Uutinen uutinen = uutinenRepository.getOne(id);
    // tarkista kirjoittjat ja gategoriat sekä poista kaikista niistä joissa on.
    
        uutinenRepository.deleteById(id);
         
    }
}