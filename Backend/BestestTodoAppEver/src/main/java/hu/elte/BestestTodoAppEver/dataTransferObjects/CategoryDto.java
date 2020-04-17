package hu.elte.BestestTodoAppEver.dataTransferObjects;

import lombok.Data;

@Data
public class CategoryDto {
    Long id;
    String name;
    String owner_id;
    String color;
}
