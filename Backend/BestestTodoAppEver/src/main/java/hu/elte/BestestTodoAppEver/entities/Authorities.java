package hu.elte.BestestTodoAppEver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Authorities {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "username")
    Users username;

    @Column
    String authority;
}
