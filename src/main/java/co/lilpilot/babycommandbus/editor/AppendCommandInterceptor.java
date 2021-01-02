package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandInterceptor;
import co.lilpilot.babycommandbus.core.PreCommandInterceptor;
import org.springframework.stereotype.Component;

@Component
@PreCommandInterceptor(commands = {AppendCommand.class})
public class AppendCommandInterceptor implements ICommandInterceptor<AppendCommand> {

    @Override
    public void preIntercept(AppendCommand command) {
        System.out.println("append: " + command.getContent());
    }

}
