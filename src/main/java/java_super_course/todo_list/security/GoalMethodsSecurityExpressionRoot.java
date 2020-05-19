package java_super_course.todo_list.security;

import java.util.List;
import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.services.TodoService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class GoalMethodsSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private GoalService goalService;
    private TodoService todoService;

    public GoalMethodsSecurityExpressionRoot(Authentication authentication, GoalService goalService, TodoService todoService) {
        super(authentication);
        this.goalService = goalService;
        this.todoService = todoService;
    }

    public boolean isGoalAuthor(Long goalId) {
        if (goalId == null) {
            return true;
        }
        User user = (User) this.getPrincipal();
        Goal goal = goalService.getGoalById(goalId);
        return user.equals(goal.getAuthor());
    }

    public boolean isTodoAuthor(Long todoId) {
        if (todoId == null) {
            return true;
        }
        User user = (User) this.getPrincipal();
        Todo todo = todoService.getTodoEditModelDto(todoId);
        return user.equals(todo.getAuthor());
    }

    @Override
    public void setFilterObject(Object o) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object o) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }
}
