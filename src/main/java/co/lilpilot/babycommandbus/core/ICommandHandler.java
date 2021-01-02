package co.lilpilot.babycommandbus.core;

public interface ICommandHandler<T extends Command> {
    void execute(T command);
}
