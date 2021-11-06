package command.command3D;

import simple3D.Viewer;

import command.DrawCommand;

import java.awt.Color;

public class BgColorCommand extends Draw3DCommand {
    private int r, g, b;
    
    // This is for the reflection to work
    public BgColorCommand(){}
    
    public BgColorCommand(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 3){
            throw new RuntimeException("Background Color command requires 3 arguments. No more, no less");
        }
        
        try{
            return new BgColorCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]),
                                   Integer.parseInt(params[2]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be integers");
        }
    }
    
    public void draw3D(Viewer v){
        v.setColor(new Color(r, g, b));
    }
}
