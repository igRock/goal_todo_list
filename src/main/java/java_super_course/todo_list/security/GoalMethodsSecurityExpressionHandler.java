package java_super_course.todo_list.security;

import java_super_course.todo_list.services.GoalService;
import java_super_course.todo_list.services.TodoService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class GoalMethodsSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    private GoalService goalService;
    private TodoService todoService;

    public GoalMethodsSecurityExpressionHandler(GoalService goalService, TodoService todoService) {
        this.goalService = goalService;
        this.todoService = todoService;
    }

    @Autowired
    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();


    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
        Authentication authentication, MethodInvocation invocation) {
        GoalMethodsSecurityExpressionRoot root = new GoalMethodsSecurityExpressionRoot(authentication, goalService, todoService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }


}
