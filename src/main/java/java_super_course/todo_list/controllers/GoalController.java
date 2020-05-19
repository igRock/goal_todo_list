package java_super_course.todo_list.controllers;

import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.validation.GoalBelongsToUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Controller
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PreAuthorize("isGoalAuthor(#goalId)")
    @GetMapping("/delete/{goalId}")
    public String deleteGoalById(
        @PathVariable Long goalId) {
        goalService.deleteGoalById(goalId);
        return "redirect:/main";
    }

    @PreAuthorize("isGoalAuthor(#goalId)")
    @GetMapping(path = {"/create_or_update", "/create_or_update/{goalId}"})
    public String createOrUpdateGoal(Model model,
                                     @PathVariable(required = false) Long goalId) {
        Goal goal = goalService.getGoalById(goalId);
        model.addAttribute("goal", goal);
        return "goal-form";
    }

    @PostMapping("/createOrUpdateGoal")
    public String createOrUpdateGoal(Goal goal) {
        goalService.createOrUpdateGoal(goal);
        return "redirect:/main";
    }
}
