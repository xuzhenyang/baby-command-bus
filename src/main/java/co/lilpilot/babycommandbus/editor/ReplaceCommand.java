package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.Command;
import lombok.Getter;

public class ReplaceCommand extends Command {

    @Getter
    private final String content;

    public ReplaceCommand(String content) {
        this.content = content;
    }

}
