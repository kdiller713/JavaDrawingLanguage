package ui;

import parser.CommandParser;

import command.DrawCommand;

import java.util.Map;
import java.util.List;

import ui.panels.RunPanel;
import ui.panels.RunPanel.ButtonInterface;

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
    private DrawFrame displayFrame;
    
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
        
        displayFrame = DrawFrame.createUI();
        
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
            displayFrame.close();
            
            List<DrawCommand> cmds = parsers.get(parserName).parseCommands(codePanel.getText());
            displayFrame.displayCommands(cmds);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error Running", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void open(){
    
    }
    
    public void save(){
    
    }
}
