package co.lilpilot.babycommandbus.editor;

import co.lilpilot.babycommandbus.core.Command;
import co.lilpilot.babycommandbus.core.CommandBus;
import co.lilpilot.babycommandbus.core.ICommandBus;
import co.lilpilot.babycommandbus.core.ICommandInterceptor;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class CommandBusTest {

    @Test
    public void test_bus() {
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

    @Test
    public void test_bus_with_interceptors() {
        Editor editor = new Editor();
        ICommandBus commandBus = new CommandBus();
        commandBus.registerHandler(AppendCommand.class, new AppendCommandHandler(editor));
        commandBus.registerHandler(ReplaceCommand.class, new ReplaceCommandHandler(editor));
        commandBus.registerHandler(ClearCommand.class, new ClearCommandHandler(editor));
        ICommandInterceptor<Command> globalLogInterceptor = new GlobalLogInterceptor();
        commandBus.registerGlobalPreInterceptor(globalLogInterceptor);
        commandBus.registerGlobalPostInterceptor(globalLogInterceptor);
        commandBus.registerPreInterceptor(AppendCommand.class, new AppendCommandInterceptor());
        assertThat(editor.getContent()).isEqualTo("");
        commandBus.send(new AppendCommand("hello world"));
        assertThat(editor.getContent()).isEqualTo("hello world");
        commandBus.send(new ReplaceCommand("hi 2021"));
        assertThat(editor.getContent()).isEqualTo("hi 2021");
        commandBus.send(new ClearCommand());
        assertThat(editor.getContent()).isEqualTo("");
    }
}
