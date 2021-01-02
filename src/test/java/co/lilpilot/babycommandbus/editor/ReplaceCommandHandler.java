package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandHandler;

public class ReplaceCommandHandler implements ICommandHandler<ReplaceCommand> {

    private final Editor editor;

    public ReplaceCommandHandler(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(ReplaceCommand command) {
        this.editor.clear();
        this.editor.append(command.getContent());
    }
}
