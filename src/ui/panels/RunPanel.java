package ui.panels;

import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;

public class RunPanel extends JPanel {
    private JButton run, open, save;
    private JComboBox<Object> parsers;

    public RunPanel(Set<String> parsers, ButtonInterface actions){
        this.parsers = new JComboBox<Object>(parsers.toArray());
        
        run = new JButton("Run");
        run.addActionListener((event) -> {actions.run("" + this.parsers.getSelectedItem());});
        
        open = new JButton("Open");
        open.addActionListener((event) -> {actions.open();});
        
        save = new JButton("Save");
        save.addActionListener((event) -> {actions.save();});
        
        this.setLayout(new FlowLayout());
        
        this.add(save);
        this.add(open);
        this.add(this.parsers);
        this.add(run);
    }
    
    public interface ButtonInterface {
        public void run(String parserName);
        public void save();
        public void open();
    }
}
