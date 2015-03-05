package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class MemoryFrame extends JFrame
{
    private Board gameBoard;
    private JFrame frame;
    GridLayout gridL;


    public MemoryFrame(final Board gameBoard) throws HeadlessException {
	super("MyMemories");
	this.gameBoard = gameBoard;
	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();

	this.frame = new JFrame("Memory");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenus();

	JLabel yellowLabel = new JLabel();
	yellowLabel.setOpaque(true);
	yellowLabel.setBackground(new Color(248, 213, 131));
 	yellowLabel.setPreferredSize(getPreferredSize());

	MemoryComponent memoryComp = new MemoryComponent(gameBoard);

	frame.setLayout(new BorderLayout());
	frame.getContentPane().add(memoryComp, BorderLayout.CENTER);

	frame.pack();
	frame.setVisible(true);

	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		// Action i spelet
	    }
	};

	final Timer clockTimer = new Timer(500, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    private void createMenus() {
	JButton newGameButton = new JButton("New Game");
	JButton quitButton = new JButton("Quit");

	newGameButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(java.awt.event.ActionEvent e) {
		Object[] options = {"Yes, wouldn't have it any other way", "No thanks, not my game today"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Would you like to start a new game?",
								"Are you sure?",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION);
		System.exit(0);
	    }
	});
	quitButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(java.awt.event.ActionEvent e) {
		Object[] options = {"Yes, unfortunately", "No, I'm on a roll!"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Are you really sure you want to do that?",
								"No!! You can't do this! Why?!",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION);
		System.exit(0);
	    }
	});

	newGameButton.setText("New Game");
	quitButton.setText("Quit");

	JMenuBar menuBar = new JMenuBar();
	menuBar.add(newGameButton);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(quitButton);
	frame.setJMenuBar(menuBar);
    }

}

	//private JTextArea textArea;
	//this.textArea = new JTextArea(height, width);
	//Font font = new Font("monospace", Font.BOLD, 30);
	//textArea.setFont(font);
	//textArea.setText(BoardToTextConverter.convertToText(gameBoard));

/** GRIDLAYOUT!!!
gridL = new GridLayout(height, width);
JPanel memoryPanel = new JPanel(gridL);
frame.add(memoryPanel);
for (int h = 0; h < height-2; h++) {
    for (int w = 0; w < width-2; w++) {
	JButton b = new JButton(Integer.toString(h*w));
 		memoryPanel.add(b);
    }
}
 */