package java_super_course.todo_list.services;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.domain.Todo;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.dto.GoalWithActivity;
import java_super_course.todo_list.dto.GoalWithStatistics;
import java_super_course.todo_list.mappers.GoalMapper;
import java_super_course.todo_list.repository.GoalRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    private final GoalMapper goalMapper;
    private final GoalRepository goalRepository;
    private final UserService userService;

    public GoalService(GoalMapper goalMapper, GoalRepository goalRepository,
                       UserService userService) {
        this.goalMapper = goalMapper;
        this.goalRepository = goalRepository;
        this.userService = userService;
    }

    public List<GoalWithActivity> getTodoGoalDtos(Todo todo) {
        User authorizedUser = userService.getAuthorizedUser();

        List<Goal> goalsFromRepository = Lists.newArrayList(goalRepository.findGoalByAuthorId(authorizedUser.getId()));
        List<GoalWithActivity> goalWithActivities = goalMapper.toGoalWithActivity(goalsFromRepository);
        goalWithActivities.forEach(goalDto -> goalDto.setIsActive(
            goalDto.getTodos() != null && goalDto.getTodos().contains(todo)));
        return goalWithActivities;
    }

    public List<GoalWithStatistics> getUsersGoals() {
        User authorizedUser =userService.getAuthorizedUser();
        return goalMapper.toGoalWithStatistics(goalRepository.findGoalByAuthorId(authorizedUser.getId()));
    }

    public Goal getGoalById(Long id) {
        if (id == null) {
            return new Goal();
        }
        return goalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteGoalById(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        goal.getTodos().forEach(todo -> todo.getGoals().remove(goal));
        goalRepository.deleteById(id);
    }

    public void createOrUpdateGoal(Goal goal) {
        User authorizedUser =userService.getAuthorizedUser();
        goal.setAuthor(authorizedUser);
        goalRepository.save(goal);
    }
}
