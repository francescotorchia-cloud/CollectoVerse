package UOA.backend.repository;

import UOA.backend.category.Category;
import UOA.backend.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    //tutti gli item di un user
    List<Item> findByUserId(Long userId);
    //tutti gli item di una collezione
    List<Item> findByCollectionId(Long collectionId);
    //tutti gli item filtrati per categoria
    List<Item> findByCategory(Category category);
    //tutti gli item filtrati per collezione e categoria
    List<Item> findByCollectionIdAndCategory(Long collectionId, Category category);
    //tutti gli item filtrati per nome
    List<Item> findByName(String name);
}
