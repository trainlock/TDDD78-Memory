package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;


public class BoardPanel extends JPanel
{

    public BoardPanel(MemoryComponent memoryComp){

	this.setLayout(new BorderLayout());
	this.add(memoryComp, BorderLayout.CENTER);
    }
}
