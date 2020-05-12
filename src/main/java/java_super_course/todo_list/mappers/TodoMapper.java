package java_super_course.todo_list.mappers;

import java.util.List;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.dto.GoalWithActivity;
import java_super_course.todo_list.dto.TodoEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface TodoMapper {
    @Mappings({
        @Mapping(target="goalsWithActivity", source="goalsWithActivities"),
    })
    TodoEditDto toTodoEditDto(Todo todo, List<GoalWithActivity> goalsWithActivities);

    @Mappings({
        @Mapping(target="goals", source="todoEditDto.goalsWithActivity"),
    })
    Todo todoEditDtoToTodo(TodoEditDto todoEditDto);
}
