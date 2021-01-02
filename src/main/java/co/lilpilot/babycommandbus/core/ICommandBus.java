package co.lilpilot.babycommandbus.core;

public interface ICommandBus {

    void registerHandler(Class<? extends Command> commandClass, ICommandHandler<? extends Command> commandHandler);

    <T extends Command> void send(T command);
}
