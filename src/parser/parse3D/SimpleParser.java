package parser.parse3D;

import command.DrawCommand;
import command.command3D.Draw3DCommand;

import java.util.List;
import java.util.LinkedList;

// Expects lines to be <cmd>(<params>)
public class SimpleParser extends CommandParser3D {
    public List<DrawCommand> parseCommands(String program){
        String[] lines = program.split("\n");
        
        LinkedList<DrawCommand> cmds = new LinkedList<DrawCommand>();
        
        for(String l : lines){
            if(l.isEmpty()) continue;
            
            String name = getName(l);
            String[] params = getParams(l);
            cmds.add(Draw3DCommand.getCommand(name, params));
        }
        
        return cmds;
    }
    
    public String getName(String line){
        int paren = line.indexOf("(");
        return line.substring(0, paren);
    }
    
    public String[] getParams(String line){
        int paren1 = line.indexOf("(");
        int paren2 = line.indexOf(")");
        
        String[] params = line.substring(paren1 + 1, paren2).split(",");
        
        for(int i = 0; i < params.length; i++){
            params[i] = params[i].trim();
        }
        
        return params;
    }
}
