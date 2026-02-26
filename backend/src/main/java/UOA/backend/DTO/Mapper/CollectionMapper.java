package UOA.backend.DTO.Mapper;

import UOA.backend.DTO.Request.CollectionResponse;
import UOA.backend.DTO.Response.CollectionRequest;
import UOA.backend.models.Collection;

public class CollectionMapper {
    public Collection toEntity(CollectionRequest collectionRequest) {
        Collection collection = new Collection();
        collection.setTitle(collectionRequest.getTitle());
        collection.setDescription(collectionRequest.getDescription());
        return collection;
    }

    public CollectionResponse toResponse(Collection collection) {
        CollectionResponse collectionResponse = new CollectionResponse();
        collectionResponse.setTitle(collection.getTitle());
        collectionResponse.setDescription(collection.getDescription());
        collectionResponse.setUser(collectionResponse.getUser());
        collectionResponse.setItems(collection.getItems());
        return collectionResponse;
    }
}
