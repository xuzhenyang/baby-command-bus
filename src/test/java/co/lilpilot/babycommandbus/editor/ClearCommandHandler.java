package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandHandler;

public class ClearCommandHandler implements ICommandHandler<ClearCommand> {

    private final Editor editor;

    public ClearCommandHandler(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(ClearCommand command) {
        this.editor.clear();
    }
}
