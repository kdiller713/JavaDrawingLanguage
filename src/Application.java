import ui.IDE;

import parser.CommandParser;
import parser.parse2D.SimpleParser;

import java.util.HashMap;

public class Application{
    public static void main(String[] args){
        HashMap<String, CommandParser> parsers = new HashMap<String, CommandParser>();
        parsers.put("Simple", new SimpleParser());
        
        IDE ide = IDE.createAndDisplayUI(parsers);
    }
}
