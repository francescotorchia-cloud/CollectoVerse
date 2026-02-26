package UOA.backend.DTO.Response;

import UOA.backend.models.Category;
import UOA.backend.models.Collection;
import UOA.backend.models.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class ItemResponse {
    private String nome;
    private String description;
    private Category category;
    private LocalDateTime date;
    private User user;
    private List<Collection> collections;
}
