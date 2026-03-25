package UOA.backend.repository;

import UOA.backend.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    //restituisce la lista delle collezioni di quell'utente by id
    List<Collection> findByUserId(UUID userId);
    //restituisce la lista delle collezioni per titolo
    List<Collection> findByTitle(String title);
    //verifica se quell'utente ha già una collezione con quel titolo
    boolean existsByUserIdAndTitle(UUID userId, String title);
    //ritorna il numero di collezioni per utente
    long countByUserId(Long userId);
}
