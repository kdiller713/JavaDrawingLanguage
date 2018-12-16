package parser;

import command.DrawCommand;

import java.util.List;

public interface CommandParser {
    /**
     * Converts a program to the list of draw commands that will be displayed.
     */
    public List<DrawCommand> parseCommands(String program);
}
