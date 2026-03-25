package UOA.backend.service;

import UOA.backend.models.Collection;
import UOA.backend.models.User;
import UOA.backend.repository.CollectionRepository;
import UOA.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    public Collection createCollection(UUID userId, Collection collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collezione non valida");
        }
        if (collection.getTitle() == null || collection.getTitle().isBlank()) {
            throw new IllegalArgumentException("Titolo obbligatorio");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User non trovato"));

        if (collectionRepository.existsByUserIdAndTitle(userId, collection.getTitle())) {
            throw new IllegalArgumentException("Collezione gia presente per questo utente");
        }

        collection.setUser(user);
        return collectionRepository.save(collection);
    }

    public Collection getCollection(Long id) {
        return collectionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));
    }

    public List<Collection> getUserCollections(UUID userId) {
        if (userId == null) throw new IllegalArgumentException("Id non valido");
        if (userRepository.findById(userId).isEmpty()) throw new EntityNotFoundException("User non trovato");
        return collectionRepository.findByUserId(userId);
    }

    public List<Collection> getCollectionsByTitle(String title) {
        if (title != null && !title.isBlank()) return collectionRepository.findByTitle(title);
        else throw new IllegalArgumentException("Titolo obbligatorio");
    }

    public void deleteCollection(Long id) {
        if (!collectionRepository.existsById(id)) {
            throw new EntityNotFoundException("Collezione non trovata");
        }
        collectionRepository.deleteById(id);
    }

    public Collection updateCollection(Long id, String title, String description) {
        Collection collection = collectionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));
        if (title != null && !title.isBlank()) {
            collection.setTitle(title);
        }
        collection.setDescription(description);
        return collectionRepository.save(collection);
    }
}
