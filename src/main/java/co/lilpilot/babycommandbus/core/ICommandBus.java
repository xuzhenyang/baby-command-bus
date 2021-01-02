package co.lilpilot.babycommandbus.core;

public interface ICommandBus {

    void registerHandler(Class<? extends Command> commandClass, ICommandHandler<? extends Command> commandHandler);

    <T extends Command> void send(T command);

    void registerGlobalPreInterceptor(ICommandInterceptor<Command> preInterceptor);

    void registerGlobalPostInterceptor(ICommandInterceptor<Command> postInterceptor);

    void registerPreInterceptor(Class<? extends Command> commandClass, ICommandInterceptor<? extends Command> commandInterceptor);

    void registerPostInterceptor(Class<? extends Command> commandClass, ICommandInterceptor<? extends Command> commandInterceptor);
}
