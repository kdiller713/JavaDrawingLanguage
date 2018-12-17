package ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

import java.awt.Graphics;

import java.util.List;

import command.DrawCommand;

// This will be used inplace of a panel next to it
public class DrawFrame extends JPanel {
    private List<DrawCommand> drawCommands;
    private JFrame frame;

    private static class DrawFrameRef {
        public DrawFrame ref;
    }
    
    public static DrawFrame createUI(){
        if(SwingUtilities.isEventDispatchThread()){
            return new DrawFrame();
        }else{
            final DrawFrameRef ref = new DrawFrameRef();
            
            try{
                SwingUtilities.invokeAndWait(() -> {ref.ref = new DrawFrame();});
            }catch(Exception e){
                System.err.println("Error creating DrawFrame: " + e.getMessage());
                e.printStackTrace();
            }
            
            return ref.ref;
        }
    }
    
    public DrawFrame(){
        frame = new JFrame("Graphics Display");
        
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(this);
    }
    
    public void close(){
        frame.setVisible(false);
    }
    
    public void displayCommands(List<DrawCommand> cmds){
        drawCommands = cmds;
        frame.repaint();
        frame.setVisible(true);
    }
    
    public void paintComponent(Graphics g){
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        for(DrawCommand c : drawCommands){
            c.draw2D(g);
        }
    }
}
