package it.iad2.kezappunoserver.repository;

import it.iad2.kezappunoserver.model.Messaggio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessaggioRepository extends JpaRepository<Messaggio, Long>{
    //public List<Messaggio> findByAliasDestinatarioOrAliasDestinatario(String destinatario, String nulla);
    public List<Messaggio> findByAliasDestinatarioEqualsOrAliasDestinatarioIsNull(String destinatario);
}
