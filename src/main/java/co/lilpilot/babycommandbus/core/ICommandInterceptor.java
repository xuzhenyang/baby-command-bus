package co.lilpilot.babycommandbus.core;

public interface ICommandInterceptor<T extends Command> {
    default void preIntercept(T command) {}
    default void postIntercept(T command) {}
}
