package java_super_course.todo_list.mappers;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.dto.GoalWithActivity;
import java_super_course.todo_list.dto.GoalWithStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface GoalMapper {
    GoalWithActivity toGoalWithActivity(Goal goal);

    List<GoalWithActivity> toGoalWithActivity(Collection<Goal> goals);

    @Mapping(target = "numberOfTodos", expression = "java(Long.valueOf(goal.getTodos().size()))")
    @Mapping(target = "numberOfCompletedTodos", expression = "java(getNumberOfCompletedTodos(goal.getTodos()))")
    @Mapping(target = "percent", expression = "java(getPercent(goal.getTodos()))")
    GoalWithStatistics toGoalWithStatistics(Goal goal);

    List<GoalWithStatistics> toGoalWithStatistics(Collection<Goal> goals);


    default Long getNumberOfCompletedTodos(Set<Todo> todos) {
        return todos.stream().filter(Todo::getIsDone).count();
    }

    default Double getPercent(Set<Todo> todos) {
        if (todos.size() == 0) {
            return 0.0;
        }
        return 100 * (double) getNumberOfCompletedTodos(todos) / (double) todos.size();
    }
}
