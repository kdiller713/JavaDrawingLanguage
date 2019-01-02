package ui.frames;

import command.DrawCommand;

import java.util.List;

public interface DrawFrame {
    public void close();
    
    public void displayCommands(List<DrawCommand> cmds);
}
