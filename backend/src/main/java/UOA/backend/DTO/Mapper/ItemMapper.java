package UOA.backend.DTO.Mapper;


import UOA.backend.DTO.Response.ItemResponse;
import UOA.backend.DTO.Request.ItemRequest;
import UOA.backend.models.Item;

public class ItemMapper {

    public Item toEntity(ItemRequest itemRequest) {
        if (itemRequest == null) return null;
        Item item = new Item();
        item.setName(itemRequest.getNome());
        item.setDescription(itemRequest.getDescription());
        item.setCategory(itemRequest.getCategory());
        item.setDate(itemRequest.getDate());
        return item;
    }

    public ItemResponse toResponse(Item item) {
        if (item == null) return null;
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setNome(item.getName());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setCategory(item.getCategory());
        itemResponse.setDate(item.getDate());
        itemResponse.setCollections(item.getCollections());
        return itemResponse;
    }
}
