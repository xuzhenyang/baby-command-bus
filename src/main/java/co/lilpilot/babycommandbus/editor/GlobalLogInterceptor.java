package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.Command;
import co.lilpilot.babycommandbus.core.ICommandInterceptor;
import co.lilpilot.babycommandbus.core.PostCommandInterceptor;
import co.lilpilot.babycommandbus.core.PreCommandInterceptor;
import org.springframework.stereotype.Component;

@Component
@PreCommandInterceptor
@PostCommandInterceptor
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
