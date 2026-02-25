package UOA.backend.controller;

import UOA.backend.models.Category;
import UOA.backend.models.Item;
import UOA.backend.service.ItemService;
import jakarta.validation.Valid;
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
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItem(id);
    }

    @GetMapping
    public List<Item> getItems(@RequestParam Long userId) {
        return itemService.getUserItems(userId);
    }

    @GetMapping("/category")
    public List<Item> getItemsByCategory(
        @RequestParam Category category
    ) {
        return itemService.getItemsByCategory(category);
    }

    @GetMapping("/collection/{collectionId}")
    public List<Item> getCollectionItems(
        @PathVariable Long collectionId
    ) {
        return itemService.getCollectionItems(collectionId);
    }

    @PutMapping("/{id}")
    public Item updateItem(
        @PathVariable Long id, @Valid @RequestBody Item item) {
        return itemService.updateItem(id, item.getName(), item.getDescription(), item.getCategory());
    }

    @PostMapping("/{itemId}/collections/{collectionId}")
    public void addItemToCollection(@PathVariable Long itemId, @PathVariable Long collectionId) {
        itemService.addItemToCollection(itemId, collectionId);
    }

    @DeleteMapping("/{itemId}/collections/{collectionId}")
    public void removeItemFromCollection(@PathVariable Long itemId, @PathVariable Long collectionId) {
        itemService.removeItemFromCollection(itemId, collectionId);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
