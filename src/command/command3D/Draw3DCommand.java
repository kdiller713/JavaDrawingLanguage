package command.command3D;

import command.DrawCommand;

import java.util.HashMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import java.awt.Graphics;

public abstract class Draw3DCommand implements DrawCommand {
    public static DrawCommand getCommand(String name, String[] params){
        Class<?> tmp = null;
    
        try{
            tmp = Class.forName("command.command3D." + name + "Command");
        }catch(Exception e){
            throw new RuntimeException("Failed to find the command \"" + name + "\"");
        }
        
        Constructor<?> c = null;
        Method m = null;
        
        try{
            c = tmp.getConstructor();
            m = tmp.getMethod("makeCommand", String[].class);
        }catch(Exception e){
            throw new RuntimeException("Failed to find the constructor: " + e.getMessage());
        }
        
        DrawCommand d = null;
        
        try{
            DrawCommand inst = DrawCommand.class.cast(c.newInstance());
            d = DrawCommand.class.cast(m.invoke(inst, new Object[]{params}));
        }catch(Exception e){
            throw new RuntimeException("Failed to create the command: " + e.getMessage());
        }
        
        return d;
    }
    
    public void draw2D(Graphics g){
        throw new RuntimeException("This object does not do 2D");
    }
}
