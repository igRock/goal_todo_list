package java_super_course.todo_list.controllers;

import java_super_course.todo_list.dto.TodoEditDto;
import java_super_course.todo_list.services.TodoService;
import java_super_course.todo_list.validation.TodoBelongsToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/delete/{todoId}")
    public String deleteTodoById(@TodoBelongsToUser @PathVariable Long todoId) {
        todoService.deleteTodoById(todoId);
        return "redirect:/main";
    }

    @GetMapping(path = {"/create_or_update", "/create_or_update/{todoId}"})
    public String createOrUpdateTodo(Model model, @TodoBelongsToUser @PathVariable(required = false) Long todoId) {
        TodoEditDto todoEditDto = todoService.getTodoEditModelDto(todoId);
        model.addAttribute("todo", todoEditDto);
        return "todo-form";
    }

    @PostMapping("/createOrUpdateTodo")
    public String createOrUpdateTodo(TodoEditDto todo) {
        todoService.createOrUpdateTodo(todo);
        return "redirect:/main";
    }

    @GetMapping("/toggle/{todoId}")
    public String toggleTodo(@TodoBelongsToUser @PathVariable Long todoId) {
        todoService.toggleTodo(todoId);
        return "redirect:/main";
    }
}
