package java_super_course.todo_list.dto;

import java_super_course.todo_list.domain.Goal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, of = {"id", "name", "description"})
@ToString(callSuper = true, of = {"id", "name", "description"})
public class GoalWithStatistics extends Goal {
    private Long numberOfTodos;
    private Long numberOfCompletedTodos;
    private Double percent;
}
