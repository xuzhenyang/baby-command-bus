package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandInterceptor;
import org.springframework.stereotype.Component;

@Component
public class AppendCommandInterceptor implements ICommandInterceptor<AppendCommand> {

    @Override
    public void preIntercept(AppendCommand command) {
        System.out.println("append: " + command.getContent());
    }

}
