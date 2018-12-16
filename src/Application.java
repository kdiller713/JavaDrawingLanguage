import ui.IDE;

import parser.CommandParser;

import java.util.HashMap;

public class Application{
    public static void main(String[] args){
        IDE ide = IDE.createAndDisplayUI(new HashMap<String, CommandParser>());
    }
}
