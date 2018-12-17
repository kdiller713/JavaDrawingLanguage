package command.command2D;

import java.awt.Graphics;
import java.awt.Color;

import command.DrawCommand;

public class ColorCommand extends Draw2DCommand {
    private int r, g, b;
    
    public ColorCommand(){
        this(0, 0, 0);
        commands.put("color", this);
    }
    
    public ColorCommand(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 3){
            throw new RuntimeException("Line command requires 3 arguments. No more, no less");
        }
        
        try{
            return new ColorCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
                                   Integer.parseInt(params[2]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be integers");
        }
    }
    
    public void draw2D(Graphics g){
        g.setColor(new Color(r, this.g, b));
    }
}
