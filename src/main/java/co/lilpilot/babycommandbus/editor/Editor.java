package co.lilpilot.babycommandbus.editor;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Editor {
    @Getter
    private String content = "";

    public void append(String content) {
        this.content += content;
    }

    public void clear() {
        this.content = "";
    }
}
