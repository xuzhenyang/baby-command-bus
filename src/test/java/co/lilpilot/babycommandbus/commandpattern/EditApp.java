package co.lilpilot.babycommandbus.commandpattern;

import co.lilpilot.babycommandbus.core.Command;
import lombok.Setter;

import java.util.List;

public class EditApp {
    @Setter
    private List<Command> commandList;

    public void run() {
        for (Command command : commandList) {
            command.execute();
        }
    }
}
