package UOA.backend.DTO.Response;

import UOA.backend.models.Item;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.List;

@Getter
@Setter
public class CollectionResponse {
    private String title;
    private String description;
    private User user;
    private List<Item> items;
}
