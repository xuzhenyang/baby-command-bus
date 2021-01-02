package co.lilpilot.babycommandbus.commandpattern;

import lombok.Getter;

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
