package hu.elte.BestestTodoAppEver.dataTransferObjects;

import hu.elte.BestestTodoAppEver.entities.Users;
import lombok.Data;

@Data
public class UserDto {
    String username;

    public UserDto(Users user) {
        this.username = user.getUsername();
    }
}
