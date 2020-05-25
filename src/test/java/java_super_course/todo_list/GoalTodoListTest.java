package java_super_course.todo_list;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.repository.GoalRepository;
import java_super_course.todo_list.repository.TodoRepository;
import java_super_course.todo_list.repository.UserRepository;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ActiveProfiles("test")
public class GoalTodoListTest {

    @ClassRule
    public static PostgreSQLContainer postgres = (PostgreSQLContainer) new PostgreSQLContainer();

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoalRepository goalRepository;

    @Test
    @Transactional
    public void should_find_todo_by_author_id(){
        User savedUser = generateNewUser();
        Todo savedTodo = generateNewTodo(savedUser);
        Goal savedGoal = generateNewGoal(savedUser);
        connectGoalAndTodo(savedGoal, savedTodo);

        List<Todo> foundTodos = todoRepository.findTodoByAuthorId(savedUser.getId());

        Assert.assertNotNull(foundTodos);
        Assert.assertEquals(foundTodos.get(0), savedTodo);
        Assert.assertEquals(foundTodos.get(0).getAuthor(), savedUser);
        Assert.assertTrue(foundTodos.get(0).getGoals().contains(savedGoal));
    }

    @Test
    public void should_find_goal_by_author_id(){
        User savedUser = generateNewUser();
        Todo savedTodo = generateNewTodo(savedUser);
        Goal savedGoal = generateNewGoal(savedUser);
        connectGoalAndTodo(savedGoal, savedTodo);

        List<Goal> foundGoals = goalRepository.findGoalByAuthorId(savedUser.getId());

        Assert.assertNotNull(foundGoals);
        Assert.assertEquals(foundGoals.get(0), savedGoal);
        Assert.assertEquals(foundGoals.get(0).getAuthor(), savedUser);
        Assert.assertTrue(foundGoals.get(0).getTodos().contains(savedTodo));
    }

    @Test
    public void should_find_user_by_email () {
        User savedUser = generateNewUser();
        User foundUser = userRepository.findByEmail(savedUser.getEmail());
        Assert.assertEquals(foundUser, savedUser);
    }

    private User generateNewUser() {
        User user = new User();
        user.setEmail("userEmail");
        user.setLogin("login");
        user.setPassword("login");
        return userRepository.save(user);
    }

    private Goal generateNewGoal(User author) {
        Goal goal = new Goal();
        goal.setAuthor(author);
        goal.setName("name");
        goal.setDescription("description");
        return goalRepository.save(goal);
    }

    private Todo generateNewTodo(User author) {
        Todo todo = new Todo();
        todo.setAuthor(author);
        todo.setDescription("description");
        todo.setIsDone(true);
        return todoRepository.save(todo);
    }

    private void connectGoalAndTodo(Goal goal, Todo todo) {
        Set<Goal> todoGoal = new HashSet<>();
        todoGoal.add(goal);
        Set<Todo> goalTodo = new HashSet<>();
        goalTodo.add(todo);

        todo.setGoals(todoGoal);
        goal.setTodos(goalTodo);

        todoRepository.save(todo);
        goalRepository.save(goal);
    }
}

