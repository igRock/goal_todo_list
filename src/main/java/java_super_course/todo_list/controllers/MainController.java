package java_super_course.todo_list.controllers;

import java.util.List;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.dto.GoalWithStatistics;
import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final TodoService todoService;
    private final GoalService goalService;

    public MainController(TodoService todoService, GoalService goalService) {
        this.todoService = todoService;
        this.goalService = goalService;
    }

    @RequestMapping("/main")
    public String getUsersTodos(ModelMap model) {
        List<Todo> todos = todoService.getUsersTodos();
        List<GoalWithStatistics> goals = goalService.getUsersGoals();
        model.addAttribute("todos", todos);
        model.addAttribute("goals", goals);
        return "main";
    }
}
