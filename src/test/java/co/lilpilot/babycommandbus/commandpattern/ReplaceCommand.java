package co.lilpilot.babycommandbus.commandpattern;

import co.lilpilot.babycommandbus.core.Command;

public class ReplaceCommand implements Command {

    private final String content;
    private final Editor editor;

    public ReplaceCommand(String content, Editor editor) {
        this.content = content;
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.clear();
        this.editor.append(this.content);
    }
}
