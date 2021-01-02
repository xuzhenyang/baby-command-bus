package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.CommandBus;
import co.lilpilot.babycommandbus.core.ICommandBus;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class CommandBusTest {

    @Test
    public void test() {
        Editor editor = new Editor();
        ICommandBus commandBus = new CommandBus();
        commandBus.registerHandler(AppendCommand.class, new AppendCommandHandler(editor));
        commandBus.registerHandler(ReplaceCommand.class, new ReplaceCommandHandler(editor));
        commandBus.registerHandler(ClearCommand.class, new ClearCommandHandler(editor));
        assertThat(editor.getContent()).isEqualTo("");
        commandBus.send(new AppendCommand("hello world"));
        assertThat(editor.getContent()).isEqualTo("hello world");
        commandBus.send(new ReplaceCommand("hi 2021"));
        assertThat(editor.getContent()).isEqualTo("hi 2021");
        commandBus.send(new ClearCommand());
        assertThat(editor.getContent()).isEqualTo("");
    }
}
