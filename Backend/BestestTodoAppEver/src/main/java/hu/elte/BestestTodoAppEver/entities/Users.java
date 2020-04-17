package hu.elte.BestestTodoAppEver.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Users implements Serializable {

    @Id
    @Column(unique = true)
    @NotNull
    String username;

    @Column
    @NotNull
    String password;

    @Column
    Boolean enabled = Boolean.TRUE;

}
