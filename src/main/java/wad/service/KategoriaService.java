package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Kategoria;
import wad.domain.Uutinen;
import wad.repository.KategoriaRepository;

@Service
public class KategoriaService {
    
    @Autowired
    private KategoriaRepository kategoriaRepository;

    public void addUutinen(Long id, Uutinen uutinen) {
        
        Kategoria kategoria = kategoriaRepository.getOne(id);
        kategoria.getUutiset().add(uutinen);
        kategoriaRepository.save(kategoria);
    }

    public void removeUutinen(Long id, Uutinen uutinen) {
        
        Kategoria kategoria = kategoriaRepository.getOne(id);
        kategoria.getUutiset().remove(uutinen);
        kategoriaRepository.save(kategoria);
    }    
    
    public void RemoveKategoria(Long id) {
        Kategoria kategoria = kategoriaRepository.getOne(id);
    // tarkista uutiset ja poista kaikista niistä joissa on.
    
        kategoriaRepository.deleteById(id);
         
    }
}