package hu.elte.BestestTodoAppEver.entities;

import hu.elte.BestestTodoAppEver.dataTransferObjects.TodoItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TodoItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column
    Boolean done;

    @Column
    String message;

    @Column(updatable = false)
    @CreationTimestamp
    Date created;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    Users owner;

    @Column
    Boolean deleted = Boolean.FALSE;

    @Column
    Date doneTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

}
