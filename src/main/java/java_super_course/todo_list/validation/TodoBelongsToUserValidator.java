package java_super_course.todo_list.validation;

import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.services.TodoService;
import java_super_course.todo_list.services.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TodoBelongsToUserValidator implements ConstraintValidator<TodoBelongsToUser, Long> {
    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoService;

    @Override
    public void initialize(TodoBelongsToUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long todoId, ConstraintValidatorContext constraintValidatorContext) {
        if (todoId == null) {
            return true;
        }
        User user = userService.getAuthorizedUser();
        Todo todo = todoService.getTodoEditModelDto(todoId);
        return todo.getAuthor().equals(user);
    }
}
