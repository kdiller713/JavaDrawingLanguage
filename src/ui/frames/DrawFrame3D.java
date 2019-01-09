package ui.frames;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.List;

import command.DrawCommand;

import simple3D.Viewer;

// This will be used inplace of a panel next to it
public class DrawFrame3D implements DrawFrame {
    private JFrame frame;
    private Viewer viewer;

    private static class DrawFrameRef {
        public DrawFrame3D ref;
    }
    
    public static DrawFrame3D createUI(){
        if(SwingUtilities.isEventDispatchThread()){
            return new DrawFrame3D();
        }else{
            final DrawFrameRef ref = new DrawFrameRef();
            
            try{
                SwingUtilities.invokeAndWait(() -> {ref.ref = new DrawFrame3D();});
            }catch(Exception e){
                System.err.println("Error creating DrawFrame: " + e.getMessage());
                e.printStackTrace();
            }
            
            return ref.ref;
        }
    }
    
    public DrawFrame3D(){
        frame = new JFrame("3D Graphics Display");
        
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        resetViewer();
    }
    
    public void close(){
        frame.setVisible(false);
        resetViewer();
    }
    
    private void resetViewer(){
        // Reset the view
        if(viewer != null)
            frame.remove(viewer);
        
        viewer = new Viewer();
        viewer.addMove();
        viewer.addZoom();
        viewer.addRotate();
        frame.add(viewer);
    }
    
    public void displayCommands(List<DrawCommand> cmds){
        for(DrawCommand c : cmds){
            c.draw3D(viewer);
        }
    
        frame.repaint();
        frame.setVisible(true);
    }
}
