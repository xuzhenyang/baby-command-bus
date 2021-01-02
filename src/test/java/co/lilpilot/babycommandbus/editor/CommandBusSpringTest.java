package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.ICommandBus;
import co.lilpilot.babycommandbus.core.ICommandInterceptor;
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
    @Autowired
    private AppendCommandHandler appendCommandHandler;
    @Autowired
    private ClearCommandHandler clearCommandHandler;
    @Autowired
    private ReplaceCommandHandler replaceCommandHandler;
    @Autowired
    @Qualifier("globalLogInterceptor")
    private ICommandInterceptor globalLogInterceptor;
    @Autowired
    @Qualifier("appendCommandInterceptor")
    private ICommandInterceptor appendCommandInterceptor;

    @Test
    public void test() {
        commandBus.registerHandler(AppendCommand.class, appendCommandHandler);
        commandBus.registerHandler(ReplaceCommand.class, replaceCommandHandler);
        commandBus.registerHandler(ClearCommand.class, clearCommandHandler);
        commandBus.registerGlobalPreInterceptor(globalLogInterceptor);
        commandBus.registerGlobalPostInterceptor(globalLogInterceptor);
        commandBus.registerPreInterceptor(AppendCommand.class, appendCommandInterceptor);
        assertThat(editor.getContent()).isEqualTo("");
        commandBus.send(new AppendCommand("hello world"));
        assertThat(editor.getContent()).isEqualTo("hello world");
        commandBus.send(new ReplaceCommand("hi 2021"));
        assertThat(editor.getContent()).isEqualTo("hi 2021");
        commandBus.send(new ClearCommand());
        assertThat(editor.getContent()).isEqualTo("");
    }
}
