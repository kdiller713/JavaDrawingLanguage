package ui;

import parser.CommandParser;

import command.DrawCommand;

import java.util.Map;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

import ui.panels.RunPanel;
import ui.panels.RunPanel.ButtonInterface;

import ui.frames.DrawFrame;
import ui.frames.DrawFrame2D;
import ui.frames.DrawFrame3D;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

public class IDE extends JFrame implements ButtonInterface {
    private Map<String, CommandParser> parsers;

    private JTextArea codePanel;
    private RunPanel runPanel;
    private DrawFrame displayFrame2D, displayFrame3D;
    private JFileChooser fileChooser;
    
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
        displayFrame3D = DrawFrame3D.createUI();
        
        runPanel = new RunPanel(parsers.keySet(), this);
        codePanel = new JTextArea();
        
        this.add(BorderLayout.SOUTH, runPanel);
        this.add(BorderLayout.CENTER, new JScrollPane(codePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750, 750);
        this.setVisible(true);
        
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
    }
    
    public void openFile(String fileName) {
        try{
            Scanner sc = new Scanner(new File(fileName));
            final StringBuilder str = new StringBuilder();
            
            boolean first = true;
            
            while(sc.hasNextLine()){
                if(!first) str.append("\n");
            
                str.append(sc.nextLine());
                first = false;
            }
            
            sc.close();
            
            SwingUtilities.invokeLater(() -> {codePanel.setText(str.toString());});
        }catch(Exception e){
            
        }
    }
    
    /* Methods implemented from the ButtonInterface */
    
    public void run(String parserName){
        try{
            displayFrame2D.close();
            displayFrame3D.close();
            CommandParser parser = parsers.get(parserName);
            
            List<DrawCommand> cmds = parser.parseCommands(codePanel.getText());
            
            if(parser.is2D()){
                displayFrame2D.displayCommands(cmds);
            }else{
                displayFrame3D.displayCommands(cmds);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error Running", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void open(){
        int choice = fileChooser.showOpenDialog(this);
        
        if(choice != JFileChooser.APPROVE_OPTION) return;
        
        File f = fileChooser.getSelectedFile();
        openFile(f.toString());
    }
    
    public void save(){
        int choice = fileChooser.showSaveDialog(this);
        
        if(choice != JFileChooser.APPROVE_OPTION) return;
        
        File f = fileChooser.getSelectedFile();
        
        try{
            FileWriter os = new FileWriter(f);
            os.write(codePanel.getText());
            os.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error saving file", "Error Saving", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
