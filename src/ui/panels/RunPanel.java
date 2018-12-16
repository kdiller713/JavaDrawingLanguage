package ui.panels;

import java.util.Set;

import javax.swing.JPanel;

public class RunPanel extends JPanel {
    public RunPanel(Set<String> parsers, ButtonInterface actions){
    
    }
    
    public interface ButtonInterface {
        public void run(String parserName);
        public void save();
        public void open();
    }
}
