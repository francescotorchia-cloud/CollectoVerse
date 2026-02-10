package UOA.backend.service;

import UOA.backend.models.Collection;
import UOA.backend.models.Item;
import UOA.backend.models.User;
import UOA.backend.repository.CollectionRepository;
import UOA.backend.repository.ItemRepository;
import UOA.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;

    public Item createItem(Long userId, Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item non valido");
        }
        if (item.getName() == null || item.getName().isBlank()) {
            throw new IllegalArgumentException("Nome obbligatorio");
        }
        if (item.getDescription() == null || item.getDescription().isBlank()) {
            throw new IllegalArgumentException("Descrizione obbligatoria");
        }
        if (item.getCategory() == null) {
            throw new IllegalArgumentException("Categoria obbligatoria");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User non trovato"));
        item.setUser(user);
        return itemRepository.save(item);
    }

    public Item getItem(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Item non trovato"));
    }

    public List<Item> getUserItems(Long userId) {
        return itemRepository.findByUserId(userId);
    }

    public List<Item> getCollectionItems(Long collectionId) {
        return itemRepository.findByCollections_Id(collectionId);
    }

    public List<Item> getItemsByCategory(Long collectionId, UOA.backend.models.Category category) {
        if (collectionId == null) {
            return itemRepository.findByCategory(category);
        }
        return itemRepository.findByCollections_IdAndCategory(collectionId, category);
    }

    public void addItemToCollection(Long itemId, Long collectionId) {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new EntityNotFoundException("Item non trovato"));
        Collection collection = collectionRepository.findById(collectionId)
            .orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));

        if (!collection.getItems().contains(item)) {
            collection.getItems().add(item);
            collectionRepository.save(collection);
        }
    }

    public void removeItemFromCollection(Long itemId, Long collectionId) {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new EntityNotFoundException("Item non trovato"));
        Collection collection = collectionRepository.findById(collectionId)
            .orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));

        if (collection.getItems().remove(item)) {
            collectionRepository.save(collection);
        }
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item non trovato");
        }
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, String name, String description, UOA.backend.models.Category category) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Item non trovato"));
        if (name != null && !name.isBlank()) {
            item.setName(name);
        }
        if (description != null && !description.isBlank()) {
            item.setDescription(description);
        }
        if (category != null) {
            item.setCategory(category);
        }
        return itemRepository.save(item);
    }
}
