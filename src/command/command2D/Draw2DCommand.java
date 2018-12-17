package command.command2D;

import command.DrawCommand;

import java.util.HashMap;

public abstract class Draw2DCommand implements DrawCommand {
    // Map from command name to command representation
    protected static HashMap<String, Draw2DCommand> commands;
    
    static{
        commands = new HashMap<String, Draw2DCommand>();
        
        new LineCommand();
        new CircleCommand();
        new ColorCommand();
    }
    
    public static DrawCommand getCommand(String name, String[] params){
        if(commands.containsKey(name)){
            return commands.get(name).makeCommand(params);
        }else{
            throw new RuntimeException("No command found named \"" + name + "\"");
        }
    }
    
    // Default implementation of 3D command
}
