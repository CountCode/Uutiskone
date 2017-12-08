package wad.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Uutinen extends AbstractPersistable<Long> {

    private String otsikko;
    private String ingressi;
    @Lob
    private byte[]kuva;
    @Lob
    private String teksti;
    private LocalDateTime julkaisuaika;
    @ManyToMany
    private List<Kirjoittaja> kirjoittajat;
    @ManyToMany
    private List<Kategoria> kategoriat;
       
    public void addKirjoittaja(Kirjoittaja kirjoittaja){
        System.out.println("Lisää kirjoittaja");
        if (this.kirjoittajat!=null){
            this.kirjoittajat.add(kirjoittaja);
        } else {
            this.kirjoittajat = new ArrayList<>();
            this.kirjoittajat.add(kirjoittaja);
        }
    }
    
    public void addKategoria(Kategoria kategoria){
        System.out.println("Lisää kategoria");
        if (this.kategoriat!=null){
            System.out.println("NOT NULL");
            this.kategoriat.add(kategoria);
        } else {
            System.out.println("NULL");
            this.kategoriat = new ArrayList<>();
            this.kategoriat.add(kategoria);
        }
    }        
    
}