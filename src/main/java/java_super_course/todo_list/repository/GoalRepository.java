package java_super_course.todo_list.repository;

import java.util.List;
import java_super_course.todo_list.domain.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Long> {
    List<Goal> findGoalByAuthorId(Long goalId);
}
