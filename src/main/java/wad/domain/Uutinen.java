package wad.domain;

import java.time.LocalDateTime;
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
    private String ingerssi;
    @Lob
    private byte[]kuva;
    private String teksti;
    private LocalDateTime julkaisuaika;
    @ManyToMany
    private List<Kirjoittaja> kirjoittajat;
    @ManyToMany
    private List<Kategoria> kategoriat;
    
    

}