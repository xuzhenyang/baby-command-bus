package co.lilpilot.babycommandbus.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PreCommandInterceptorRegister implements IRegister, ApplicationContextAware {

    @Autowired
    private CommandBus commandBus;
    private ApplicationContext applicationContext;

    @Override
    public void register(Class<?> targetClass) {
        ICommandInterceptor interceptor = (ICommandInterceptor) applicationContext.getBean(targetClass);
        PreCommandInterceptor preCommandInterceptorAnn = targetClass.getDeclaredAnnotation(PreCommandInterceptor.class);
        Class<? extends Command>[] commands = preCommandInterceptorAnn.commands();
        if (commands.length == 0) {
            commandBus.registerGlobalPreInterceptor(interceptor);
        }
        else {
            for (Class<? extends Command> command : commands) {
                commandBus.registerPreInterceptor(command, interceptor);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
