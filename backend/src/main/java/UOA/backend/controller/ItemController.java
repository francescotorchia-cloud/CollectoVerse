package UOA.backend.controller;

import UOA.backend.models.Category;
import UOA.backend.models.Item;
import UOA.backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Item createItem(@RequestParam Long userId, @RequestBody Item item) {
        return itemService.createItem(userId, item);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }

    @GetMapping
    public List<Item> getItems(
        @RequestParam(required = false) Long userId,
        @RequestParam(required = false) Long collectionId
    ) {
        if (collectionId != null) {
            return itemService.getCollectionItems(collectionId);
        }
        if (userId != null) {
            return itemService.getUserItems(userId);
        }
        return itemService.getUserItems(-1L);
    }

    @GetMapping("/by-category")
    public List<Item> getItemsByCategory(
        @RequestParam(required = false) Long collectionId,
        @RequestParam Category category
    ) {
        return itemService.getItemsByCategory(collectionId, category);
    }

    @PutMapping("/{id}")
    public Item updateItem(
        @PathVariable Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) Category category
    ) {
        return itemService.updateItem(id, name, description, category);
    }

    @PostMapping("/{id}/collections/{collectionId}")
    public void addItemToCollection(@PathVariable Long id, @PathVariable Long collectionId) {
        itemService.addItemToCollection(id, collectionId);
    }

    @DeleteMapping("/{id}/collections/{collectionId}")
    public void removeItemFromCollection(@PathVariable Long id, @PathVariable Long collectionId) {
        itemService.removeItemFromCollection(id, collectionId);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
