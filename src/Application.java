import ui.IDE;

import parser.CommandParser;

import java.util.HashMap;

public class Application{
    public static void main(String[] args){
        HashMap<String, CommandParser> parsers = new HashMap<String, CommandParser>();
        parsers.put("Simple 2D", new parser.parse2D.SimpleParser());
        parsers.put("Simple 3D", new parser.parse3D.SimpleParser());
        
        IDE ide = IDE.createAndDisplayUI(parsers);
        
        if(args.length > 0){
            ide.openFile(args[0]);
        }
    }
}
