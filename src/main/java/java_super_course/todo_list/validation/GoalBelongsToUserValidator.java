package java_super_course.todo_list.validation;

import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.services.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class GoalBelongsToUserValidator implements ConstraintValidator<GoalBelongsToUser, Long> {
    @Autowired
    private UserService userService;
    @Autowired
    private GoalService goalService;

    @Override
    public void initialize(GoalBelongsToUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long goalId, ConstraintValidatorContext constraintValidatorContext) {
        if (goalId == null) {
            return true;
        }
        User user = userService.getAuthorizedUser();
        Goal goal = goalService.getGoalById(goalId);
        return goal.getAuthor().equals(user);
    }
}
