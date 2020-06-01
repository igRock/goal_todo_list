package java_super_course.todo_list.security;

import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.services.TodoService;
import java_super_course.todo_list.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodsAuthorityEvaluator {

    private UserService userService;
    private GoalService goalService;
    private TodoService todoService;

    public MethodsAuthorityEvaluator(UserService userService,
                                     GoalService goalService, TodoService todoService) {
        this.userService = userService;
        this.goalService = goalService;
        this.todoService = todoService;
    }

    public boolean isGoalAuthor(Long goalId) {
        if (goalId == null) {
            return true;
        }
        User user = userService.getAuthorizedUser();
        Goal goal = goalService.getGoalById(goalId);
        return user.equals(goal.getAuthor());
    }

    public boolean isTodoAuthor(Long todoId) {
        if (todoId == null) {
            return true;
        }
        User user = userService.getAuthorizedUser();
        Todo todo = todoService.getTodoEditModelDto(todoId);
        return user.equals(todo.getAuthor());
    }
}
