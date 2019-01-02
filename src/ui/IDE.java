package ui;

import parser.CommandParser;

import command.DrawCommand;

import java.util.Map;
import java.util.List;

import ui.panels.RunPanel;
import ui.panels.RunPanel.ButtonInterface;

import ui.frames.DrawFrame;
import ui.frames.DrawFrame2D;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

public class IDE extends JFrame implements ButtonInterface {
    private Map<String, CommandParser> parsers;

    private JTextArea codePanel;
    private RunPanel runPanel;
    private DrawFrame displayFrame2D;
    
    private static class IDERef {
        public IDE ref;
    }
    
    public static IDE createAndDisplayUI(Map<String, CommandParser> parsers){
        if(SwingUtilities.isEventDispatchThread()){
            return new IDE(parsers);
        }else{
            final IDERef ref = new IDERef();
            
            try{
                SwingUtilities.invokeAndWait(() -> {ref.ref = new IDE(parsers);});
            }catch(Exception e){
                System.err.println("Error creating IDE: " + e.getMessage());
                e.printStackTrace();
            }
            
            return ref.ref;
        }
    }
    
    private IDE(Map<String, CommandParser> parsers){
        super("Java Drawing Language");
        this.parsers = parsers;
        
        displayFrame2D = DrawFrame2D.createUI();
        
        runPanel = new RunPanel(parsers.keySet(), this);
        codePanel = new JTextArea(50, 80);
        
        this.add(BorderLayout.SOUTH, runPanel);
        this.add(BorderLayout.CENTER, new JScrollPane(codePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    /* Methods implemented from the ButtonInterface */
    
    public void run(String parserName){
        try{
            displayFrame2D.close();
            CommandParser parser = parsers.get(parserName);
            
            List<DrawCommand> cmds = parser.parseCommands(codePanel.getText());
            
            if(parser.is2D()){
                displayFrame2D.displayCommands(cmds);
            }else{
                // Add the 3D draw frame
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error Running", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void open(){
    
    }
    
    public void save(){
    
    }
}
