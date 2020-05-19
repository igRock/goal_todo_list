package java_super_course.todo_list.security;

import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Autowired
    @Lazy
    private GoalService goalService;

    @Autowired
    @Lazy
    private TodoService todoService;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new GoalMethodsSecurityExpressionHandler(goalService, todoService);
    }
}
