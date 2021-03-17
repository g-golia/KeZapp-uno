
package it.iad2.kezappunoserver.repository;

import it.iad2.kezappunoserver.model.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessaggioRepository extends JpaRepository<Messaggio, Long>{
    
}
