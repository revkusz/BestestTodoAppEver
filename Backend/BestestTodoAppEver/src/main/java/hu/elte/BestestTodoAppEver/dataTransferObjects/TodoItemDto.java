package hu.elte.BestestTodoAppEver.dataTransferObjects;

import lombok.Data;

import java.util.Date;

@Data
public class TodoItemDto {
    Long id;
    boolean done;
    String message;
    String owner_id;
    Date created;
    Date doneTime;
    Long categor_id;
}
