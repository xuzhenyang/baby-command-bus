package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandHandler;

public class AppendCommandHandler implements ICommandHandler<AppendCommand> {

    private final Editor editor;

    public AppendCommandHandler(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(AppendCommand command) {
        this.editor.append(command.getContent());
    }
}
