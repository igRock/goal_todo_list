package java_super_course.todo_list.dto;

import java_super_course.todo_list.domain.Goal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoalWithActivity extends Goal {
    private Boolean isActive; // Активно, когда у to do есть этот goal
}
