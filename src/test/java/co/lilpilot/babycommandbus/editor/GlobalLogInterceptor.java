package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.Command;
import co.lilpilot.babycommandbus.core.ICommandInterceptor;
import org.springframework.stereotype.Component;

@Component
public class GlobalLogInterceptor implements ICommandInterceptor<Command> {
    @Override
    public void preIntercept(Command command) {
        System.out.println("--- before execute ---");
        System.out.println("command: " + command.toString());
    }

    @Override
    public void postIntercept(Command command) {
        System.out.println("--- after execute ---");
    }
}
