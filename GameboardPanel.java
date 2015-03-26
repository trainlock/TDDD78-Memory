package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.BorderLayout;

public class GameboardPanel extends JPanel
{
    private JPanel panel;
    private Board gameBoard;

    public GameboardPanel(final Board gameBoard) {
	this.gameBoard = gameBoard;

	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();

	panel = new JPanel(new BorderLayout());
    }
}
