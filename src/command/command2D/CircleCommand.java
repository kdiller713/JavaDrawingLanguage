package command.command2D;

import java.awt.Graphics;

import command.DrawCommand;

public class CircleCommand extends Draw2DCommand {
    private int x, y, r;
    
    public CircleCommand(){
        this(0, 0, 0);
        commands.put("circle", this);
    }
    
    public CircleCommand(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 3){
            throw new RuntimeException("Line command requires 3 arguments. No more, no less");
        }
        
        try{
            return new CircleCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
                                   Integer.parseInt(params[2]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be integers");
        }
    }
    
    public void draw2D(Graphics g){
        g.drawOval(x - r, y - r, 2*r, 2*r);
    }
}
