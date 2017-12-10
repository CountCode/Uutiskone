package wad.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Uutinen;

public interface UutinenRepository extends JpaRepository<Uutinen, Long> {
 //    List<Uutinen> findByKategoria(String nimi);
     List<Uutinen> findByJulkaisuaika(LocalDateTime julkaisuaika);
//     List<Uutinen> findByLukumaara(int lukum);
}