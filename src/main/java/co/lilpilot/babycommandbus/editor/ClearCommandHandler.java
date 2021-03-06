package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearCommandHandler implements ICommandHandler<ClearCommand> {

    private final Editor editor;

    @Autowired
    public ClearCommandHandler(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(ClearCommand command) {
        this.editor.clear();
    }
}
