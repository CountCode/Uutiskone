package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Kategoria extends AbstractPersistable<Long> {
    
    private String nimi;
    @ManyToMany(mappedBy = "kategoriat")
    private List<Uutinen> uutiset;
    
    public void addUutinen(Uutinen uutinen){
        if (this.uutiset!=null){
            this.uutiset.add(uutinen);
        } else {
            this.uutiset = new ArrayList<>();
            this.uutiset.add(uutinen);
        }
    }
}