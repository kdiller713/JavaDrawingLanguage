package ui.panels;

import javax.swing.JPanel;

import java.util.List;

import command.DrawCommand;

public abstract class Draw extends JPanel {
    /**
     * This draws the list of commands in the proper canvas (either 2D or 3D space).
     */
    public abstract void drawCommands(List<DrawCommand> cmds);
}
