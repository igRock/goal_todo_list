package java_super_course.todo_list.repository;

import java.util.List;
import java_super_course.todo_list.domain.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findTodoByAuthorId(Long userId);
}
