package UOA.backend.DTO.Response;

import UOA.backend.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ItemRequest {
    private String nome;
    private String description;
    private Category category;
    private LocalDateTime date;
}
