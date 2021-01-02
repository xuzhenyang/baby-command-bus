package co.lilpilot.babycommandbus.commandpattern;

import co.lilpilot.babycommandbus.core.Command;

public class AppendCommand implements Command {

    private final String content;
    private final Editor editor;

    public AppendCommand(String content, Editor editor) {
        this.content = content;
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.append(content);
    }
}
