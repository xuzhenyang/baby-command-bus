package co.lilpilot.babycommandbus.core;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostCommandInterceptor {

    Class<? extends Command>[] commands() default {};

}
