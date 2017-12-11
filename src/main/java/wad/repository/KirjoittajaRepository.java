package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Account;
import wad.domain.Kirjoittaja;

public interface KirjoittajaRepository extends JpaRepository<Kirjoittaja, Long> {
     List<Kirjoittaja> findByNimi(String nimi);
     Kirjoittaja findByAccount(Account account);
}