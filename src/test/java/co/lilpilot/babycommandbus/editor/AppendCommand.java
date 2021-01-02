package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.Command;
import lombok.Getter;

public class AppendCommand extends Command {

    @Getter
    private final String content;

    public AppendCommand(String content) {
        this.content = content;
    }

}
