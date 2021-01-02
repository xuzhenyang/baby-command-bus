package co.lilpilot.babycommandbus.commandpattern;

import co.lilpilot.babycommandbus.core.Command;

public class ClearCommand implements Command {

    private final Editor editor;

    public ClearCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.clear();
    }
}
