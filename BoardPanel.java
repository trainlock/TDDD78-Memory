package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;


public class BoardPanel extends JPanel
{
    private Board myBoard;
    private MemoryComponent memoryComp;

    public BoardPanel(Board myBoard, MemoryComponent memoryComp){
	this.myBoard = myBoard;
	this.memoryComp = memoryComp;

	this.setLayout(new BorderLayout());
	this.add(memoryComp, BorderLayout.CENTER);
    }
}
