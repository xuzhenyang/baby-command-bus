package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppendCommandHandler implements ICommandHandler<AppendCommand> {

    private final Editor editor;

    @Autowired
    public AppendCommandHandler(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(AppendCommand command) {
        this.editor.append(command.getContent());
    }
}
