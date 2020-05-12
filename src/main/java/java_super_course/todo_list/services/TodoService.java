package java_super_course.todo_list.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.dto.TodoEditDto;
import java_super_course.todo_list.dto.GoalWithActivity;
import java_super_course.todo_list.mappers.TodoMapper;
import java_super_course.todo_list.repository.TodoRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final GoalService goalService;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper,
                       GoalService goalService, UserService userService) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
        this.goalService = goalService;
        this.userService = userService;
    }

    public List<Todo> getUsersTodos() {
        User authorizedUser = userService.getAuthorizedUser();
        List<Todo> usersTodo = todoRepository.findTodoByAuthorId(authorizedUser.getId());
        usersTodo.sort(Comparator.comparing(Todo::getIsDone));
        return usersTodo;
    }

    private Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(EntityNotFoundException::new); // Посмотреть потом
    }

    public TodoEditDto getTodoEditModelDto(Long todoId) {
        Todo todo = todoId != null ? getTodoById(todoId) : new Todo();
        List<GoalWithActivity> goalsWithActivities = goalService.getTodoGoalDtos(todo);
        return todoMapper.toTodoEditDto(todo, goalsWithActivities);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public void createOrUpdateTodo(TodoEditDto todoEditDto) {
        User authorizedUser = userService.getAuthorizedUser();

        todoEditDto.setAuthor(authorizedUser);
        todoEditDto.setGoalsWithActivity(todoEditDto.getGoalsWithActivity().stream()
                                      .peek(goal -> goal.setAuthor(authorizedUser))
                                      .filter(GoalWithActivity::getIsActive)
                                      .collect(Collectors.toList()));
        Todo newTodo = todoMapper.todoEditDtoToTodo(todoEditDto);
        todoRepository.save(newTodo);
    }

    public void toggleTodo(Long todoId) {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setIsDone(!todo.getIsDone());
            todoRepository.save(todo);
        }
    }
}
