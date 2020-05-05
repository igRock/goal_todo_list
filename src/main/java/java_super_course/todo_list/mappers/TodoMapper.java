package java_super_course.todo_list.mappers;

import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.dto.TodoEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface TodoMapper {
    @Mappings({
        @Mapping(target="goalsWithActivity", source="todo.goals"),
    })
    TodoEditDto toTodoEditDto(Todo todo);

    @Mappings({
        @Mapping(target="goals", source="todoEditDto.goalsWithActivity"),
    })
    Todo todoEditDtoToTodo(TodoEditDto todoEditDto);
}
