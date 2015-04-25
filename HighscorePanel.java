package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;

public class HighscorePanel extends JPanel
{
    public JTextArea textArea;
    public static final int TEXT_AREA_HEIGHT = 100;
    public static final int TEXT_AREA_WIDTH = 100;
    public static final int FONT_SIZE = 20;

    public HighscorePanel(){
	this.textArea= new JTextArea(" Highscore ");
	textArea.setSize(new Dimension(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT));
	textArea.setFont(new Font("MONOSPACE", Font.PLAIN, FONT_SIZE));
	textArea.setEnabled(false);
	textArea.setVisible(true);

	this.add(textArea);
    }
}
