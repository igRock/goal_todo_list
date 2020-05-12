package java_super_course.todo_list.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TodoBelongsToUserValidator.class)
public @interface TodoBelongsToUser {
    String message() default "Todo doesn't belong to this user";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
