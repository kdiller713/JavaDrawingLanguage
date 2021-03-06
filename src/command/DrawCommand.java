package command;

import java.awt.Graphics;

import simple3D.Viewer;

/**
 * This interface is used by the parser to return what is to be drawn on the display.
 * There is a method for 2D and a method for 3D. Implementing classes do not need to have an
 * implementation for both methods.
 */
public interface DrawCommand {
    public DrawCommand makeCommand(String[] params);

    public void draw2D(Graphics g); 
    
    public void draw3D(Viewer v); 
}
