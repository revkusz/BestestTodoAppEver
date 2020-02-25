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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    User owner;

    @Column
    String color;

    @Column
    Boolean deleted = Boolean.FALSE;

    @Column
    Boolean showAll = Boolean.FALSE;

}
