package co.lilpilot.babycommandbus.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Component
public class CommandHandlerRegister implements IRegister, ApplicationContextAware {

    private final CommandBus commandBus;

    private ApplicationContext applicationContext;

    @Autowired
    public CommandHandlerRegister(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void register(Class<?> targetClass) {
        Type[] genericInterfaces = targetClass.getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) genericInterfaces[0];
        Class<? extends Command> commandClass = (Class<? extends Command>) parameterizedType.getActualTypeArguments()[0];
        if (commandClass == null || !Command.class.isAssignableFrom(commandClass)) {
            throw new RuntimeException("target class is not the command class");
        }
        ICommandHandler<? extends Command> handler = (ICommandHandler<? extends Command>) applicationContext.getBean(targetClass);
        commandBus.registerHandler(commandClass, handler);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
