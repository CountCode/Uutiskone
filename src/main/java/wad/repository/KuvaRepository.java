package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.KuvaObjekti;

public interface KuvaRepository  extends JpaRepository<KuvaObjekti, Long> {
    
}