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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(unique = true)
    @NotNull
    String nev;

    @Column
    String email;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at;

}
