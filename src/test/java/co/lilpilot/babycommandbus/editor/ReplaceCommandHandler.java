package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplaceCommandHandler implements ICommandHandler<ReplaceCommand> {

    private final Editor editor;

    @Autowired
    public ReplaceCommandHandler(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(ReplaceCommand command) {
        this.editor.clear();
        this.editor.append(command.getContent());
    }
}
