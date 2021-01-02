package co.lilpilot.babycommandbus.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PostCommandInterceptorRegister implements IRegister, ApplicationContextAware {

    @Autowired
    private CommandBus commandBus;
    private ApplicationContext applicationContext;

    @Override
    public void register(Class<?> targetClass) {
        ICommandInterceptor interceptor = (ICommandInterceptor) applicationContext.getBean(targetClass);
        PostCommandInterceptor postCommandInterceptorAnn = targetClass.getDeclaredAnnotation(PostCommandInterceptor.class);
        Class<? extends Command>[] commands = postCommandInterceptorAnn.commands();
        if (commands.length == 0) {
            commandBus.registerGlobalPostInterceptor(interceptor);
        }
        else {
            for (Class<? extends Command> command : commands) {
                commandBus.registerPostInterceptor(command, interceptor);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
