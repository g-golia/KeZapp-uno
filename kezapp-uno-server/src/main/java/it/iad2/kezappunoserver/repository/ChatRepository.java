
package it.iad2.kezappunoserver.repository;

import it.iad2.kezappunoserver.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
    public Chat findByNickname(String nickname);
    public Chat findBySessione(String sessione);
}
