package co.lilpilot.babycommandbus.commandpattern;

import co.lilpilot.babycommandbus.core.Command;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class CommandPatternTest {

    @Test
    public void test() {
        EditApp editApp = new EditApp();
        List<Command> commandList = Lists.newArrayList();
        editApp.setCommandList(commandList);
        Editor editor = new Editor();

        assertThat(editor.getContent()).isEqualTo("");
        commandList.add(new AppendCommand("hello world", editor));
        editApp.run();
        assertThat(editor.getContent()).isEqualTo("hello world");
        commandList.add(new ReplaceCommand("replace test", editor));
        editApp.run();
        assertThat(editor.getContent()).isEqualTo("replace test");
        commandList.add(new ClearCommand(editor));
        editApp.run();
        assertThat(editor.getContent()).isEqualTo("");
    }

}
