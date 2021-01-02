package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandBus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommandBusSpringTest {

    @Autowired
    @Qualifier("commandBus")
    private ICommandBus commandBus;
    @Autowired
    private Editor editor;

    @Test
    public void test() {
        assertThat(editor.getContent()).isEqualTo("");
        commandBus.send(new AppendCommand("hello world"));
        assertThat(editor.getContent()).isEqualTo("hello world");
        commandBus.send(new ReplaceCommand("hi 2021"));
        assertThat(editor.getContent()).isEqualTo("hi 2021");
        commandBus.send(new ClearCommand());
        assertThat(editor.getContent()).isEqualTo("");
    }
}
