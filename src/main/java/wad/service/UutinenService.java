package wad.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Kategoria;
import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;
import wad.repository.KategoriaRepository;
import wad.repository.KirjoittajaRepository;
import wad.repository.KuvaRepository;
import wad.repository.UutinenRepository;

@Service
public class UutinenService {
    
    @Autowired
    private UutinenRepository uutinenRepository;
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private KuvaRepository kuvaRepository;    
    
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
    // tarkista kirjoittajat ja gategoriat sekä poista kaikista niistä joissa on.
    
        uutinenRepository.deleteById(id);
         
    }

    @Transactional
    public void LiitaKirjoittaja(Long uutinenId, Long kirjoittajaId) {
        Uutinen uutinen = uutinenRepository.getOne(uutinenId);
        Kirjoittaja kirjoittaja = kirjoittajaRepository.getOne(kirjoittajaId);

        uutinen.addKirjoittaja(kirjoittaja);
        kirjoittaja.addUutinen(uutinen);
    }

    @Transactional
    public void LiitaKategoria(Long uutinenId, Long kategoriaId) {
        Uutinen uutinen = uutinenRepository.getOne(uutinenId);
        Kategoria kategoria = kategoriaRepository.getOne(kategoriaId);

        uutinen.addKategoria(kategoria);
        kategoria.addUutinen(uutinen);
    }    

    public void poistaUutinen(Uutinen uutinen) {
        for (Kirjoittaja kirjoittaja : uutinen.getKirjoittajat()){
            kirjoittaja.getUutiset().remove(uutinen.getId());
            kirjoittajaRepository.save(kirjoittaja);
        }

        for (Kategoria kategoria : uutinen.getKategoriat()){
            kategoria.getUutiset().remove(uutinen.getId());
            kategoriaRepository.save(kategoria);
        }
        
        Long kuvaObjektiId = uutinen.getKuvaObjektiId();
        if (kuvaObjektiId!= null){
            kuvaRepository.deleteById(uutinen.getKuvaObjektiId());
        }
        
        uutinenRepository.deleteById(uutinen.getId());
    }
    
}