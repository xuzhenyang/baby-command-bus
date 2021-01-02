package co.lilpilot.babycommandbus.core;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommandBus implements ICommandBus{

    private final Map<Class<? extends Command>, ICommandHandler<? extends Command>> commandHandlerMap = new HashMap<>();

    private final LinkedList<ICommandInterceptor> globalPreInterceptors = Lists.newLinkedList();

    private final LinkedList<ICommandInterceptor> globalPostInterceptors = Lists.newLinkedList();

    private final ListMultimap<Class, ICommandInterceptor> preInterceptors = LinkedListMultimap.create();

    private final ListMultimap<Class, ICommandInterceptor> postInterceptors = LinkedListMultimap.create();

    @Override
    public void registerHandler(Class<? extends Command> commandClass, ICommandHandler<? extends Command> commandHandler) {
        this.commandHandlerMap.put(commandClass, commandHandler);
    }

    @Override
    public <T extends Command> void send(T command) {
        ICommandHandler<T> commandHandler = (ICommandHandler<T>) this.commandHandlerMap.get(command.getClass());
        if (commandHandler != null) {
            executePreIntercept(command);
            try {
                commandHandler.execute(command);
            }
            finally {
                executePostIntercept(command);
            }
        }
    }

    private <T extends Command> void executePreIntercept(T command) {
        for (ICommandInterceptor globalPreInterceptor : this.globalPreInterceptors) {
            globalPreInterceptor.preIntercept(command);
        }
        Class<?> superClass = command.getClass();
        while (Command.class.isAssignableFrom(superClass)) {
            List<ICommandInterceptor> preInterceptors = this.preInterceptors.get(superClass);
            for (ICommandInterceptor preInterceptor : preInterceptors) {
                preInterceptor.preIntercept(command);
            }
            superClass = superClass.getSuperclass();
        }
    }

    private <T extends Command> void executePostIntercept(T command) {
        for (ICommandInterceptor globalPostInterceptor : this.globalPostInterceptors) {
            globalPostInterceptor.postIntercept(command);
        }
        Class<?> superClass = command.getClass();
        while (Command.class.isAssignableFrom(superClass)) {
            List<ICommandInterceptor> postInterceptors = this.postInterceptors.get(superClass);
            for (ICommandInterceptor postInterceptor : postInterceptors) {
                postInterceptor.postIntercept(command);
            }
            superClass = superClass.getSuperclass();
        }
    }

    @Override
    public void registerGlobalPreInterceptor(ICommandInterceptor<Command> preInterceptor) {
        this.globalPreInterceptors.add(preInterceptor);
    }

    @Override
    public void registerGlobalPostInterceptor(ICommandInterceptor<Command> postInterceptor) {
        this.globalPostInterceptors.add(postInterceptor);
    }

    @Override
    public void registerPreInterceptor(Class<? extends Command> commandClass, ICommandInterceptor<? extends Command> commandInterceptor) {
        this.preInterceptors.put(commandClass, commandInterceptor);
    }

    @Override
    public void registerPostInterceptor(Class<? extends Command> commandClass, ICommandInterceptor<? extends Command> commandInterceptor) {
        this.postInterceptors.put(commandClass, commandInterceptor);
    }
}
