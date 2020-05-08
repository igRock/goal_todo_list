package java_super_course.todo_list.dto;

import java.util.ArrayList;
import java.util.List;
import java_super_course.todo_list.domain.Todo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TodoEditDto extends Todo {
    private List<GoalWithActivity> goalsWithActivity = new ArrayList<>();
}
