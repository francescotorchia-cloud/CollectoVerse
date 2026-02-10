package UOA.backend.controller;

import UOA.backend.models.Collection;
import UOA.backend.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public Collection createCollection(@RequestParam Long userId, @RequestBody Collection collection) {
        return collectionService.createCollection(userId, collection);
    }

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable Long id) {
        return collectionService.getCollection(id);
    }

    @GetMapping
    public List<Collection> getCollections(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return collectionService.getCollectionsByTitle("");
        }
        return collectionService.getUserCollections(userId);
    }

    @GetMapping("/by-title")
    public List<Collection> getCollectionsByTitle(@RequestParam String title) {
        return collectionService.getCollectionsByTitle(title);
    }

    @PutMapping("/{id}")
    public Collection updateCollection(
        @PathVariable Long id,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String description
    ) {
        return collectionService.updateCollection(id, title, description);
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
    }
}
