package java_super_course.todo_list.controllers;

import java.util.Optional;
import java_super_course.todo_list.domain.Goal;
import java_super_course.todo_list.services.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/delete/{goalId}")
    public String deleteGoalById(@PathVariable Long goalId) {
        goalService.deleteGoalById(goalId);
        return "redirect:/main";
    }

    @GetMapping(path = {"/create_or_update", "/create_or_update/{goalId}"})
    public String createOrUpdateGoal(Model model, @PathVariable Optional<Long> goalId) {
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
