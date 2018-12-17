package command.command2D;

import java.awt.Graphics;

import command.DrawCommand;

public class LineCommand extends Draw2DCommand {
    private int x1, y1, x2, y2;
    
    public LineCommand(){
        this(0, 0, 0, 0);
        commands.put("line", this);
    }
    
    public LineCommand(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 4){
            throw new RuntimeException("Line command requires 4 arguments. No more, no less");
        }
        
        try{
            return new LineCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
                                   Integer.parseInt(params[2]), Integer.parseInt(params[3]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be integers");
        }
    }
    
    public void draw2D(Graphics g){
        g.drawLine(x1, y1, x2, y2);
    }
}
