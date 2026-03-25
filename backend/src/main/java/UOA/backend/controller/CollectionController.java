package UOA.backend.controller;

import UOA.backend.models.Collection;
import UOA.backend.service.CollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public Collection createCollection(@RequestParam UUID userId, @Valid @RequestBody Collection collection) {
        return collectionService.createCollection(userId, collection);
    }

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable Long id) {
        return collectionService.getCollection(id);
    }

    @GetMapping
    public List<Collection> getCollections(@RequestParam UUID userId) {
        return collectionService.getUserCollections(userId);
    }

    @GetMapping("/{title}")
    public List<Collection> getCollectionsByTitle(@PathVariable String title) {
        return collectionService.getCollectionsByTitle(title);
    }

    @PutMapping("/{id}")
    public Collection updateCollection(
        @PathVariable Long id,
        @Valid @RequestBody Collection collection
    ) {
        return collectionService.updateCollection(id, collection.getTitle(), collection.getDescription());
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
    }
}
