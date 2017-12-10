package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Kategoria;
import wad.domain.Uutinen;

public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {
     List<Uutinen> findByNimi(String nimi);
}