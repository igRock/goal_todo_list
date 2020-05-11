package java_super_course.todo_list.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"id", "description", "isDone"})
@ToString(of = {"id", "description", "isDone"})
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "is_done")
    private Boolean isDone = false;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User author;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "goal_todo",
        joinColumns = @JoinColumn(name = "todo_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name = "goal_id", referencedColumnName="id"))
    private Set<Goal> goals = new HashSet<>();
}
