package co.lilpilot.babycommandbus.core;

import java.util.HashMap;
import java.util.Map;

public class CommandBus implements ICommandBus{

    private Map<Class<? extends Command>, ICommandHandler<? extends Command>> commandHandlerMap = new HashMap<>();

    @Override
    public void registerHandler(Class<? extends Command> commandClass, ICommandHandler<? extends Command> commandHandler) {
        this.commandHandlerMap.put(commandClass, commandHandler);
    }

    @Override
    public <T extends Command> void send(T command) {
        ICommandHandler<T> commandHandler = (ICommandHandler<T>) this.commandHandlerMap.get(command.getClass());
        commandHandler.execute(command);
    }
}
