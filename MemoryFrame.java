package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


public class MemoryFrame extends JFrame implements MouseListener
{
    private JFrame frame;
    private MemoryComponent memoryComp;
    private TileActionManager tileManager;
    public boolean boardEnabled;

    public MemoryFrame(final Board gameBoard) {
	super("MyMemories");
	// Kommer behöva var mycket större än det är nu!
	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();

	memoryComp = new MemoryComponent(gameBoard);
	tileManager = new TileActionManager(memoryComp, gameBoard);
	tileManager.setStartTileValue(height, width);
	this.frame = new JFrame("Memory");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenus();

	frame.setLayout(new BorderLayout());
	frame.getContentPane().add(memoryComp, BorderLayout.CENTER);
	frame.getContentPane().addMouseListener(this);
	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);
    }

    private void createMenus() {
	JButton newGameButton = new JButton("New Game");
	JButton quitButton = new JButton("Quit");

	newGameButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
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
	    @Override public void actionPerformed(ActionEvent e) {
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

    public void showWinMessage() {
	if (tileManager.isAllSameTile()) {
	    JOptionPane.showMessageDialog(this, "You have beaten the game! Congrats CHAMP!");
	}
    }

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {
	this.boardEnabled = tileManager.isBoardEnabled();
	System.out.println(boardEnabled + " mouseEvent");
	if (boardEnabled){
	    // Ha en state på mouseEvent som säger om man kan änvända eller ej.
	    int xCoord = e.getX();
	    int yCoord = e.getY();
	    // VIKTIGT BYT METODNAMN!!!!
	    System.out.println("före");
	    tileManager.yolo(xCoord, yCoord);
	    System.out.println("efter");
	}
    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }

}

	//private JTextArea textArea;
	//this.textArea = new JTextArea(height, width);
	//Font font = new Font("monospace", Font.BOLD, 30);
	//textArea.setFont(font);
	//textArea.setText(BoardToTextConverter.convertToText(gameBoard));


/**
JLabel yellowLabel = new JLabel();
yellowLabel.setOpaque(true);
yellowLabel.setBackground(new Color(248, 213, 131));
	yellowLabel.setPreferredSize(getPreferredSize());



 private ArrayList<TileButton> myButtons;

 memoryPanel.setSize(getPreferredSize());
	memoryPanel.setLayout(new GridLayout(4, 4));

	for (int h = 0; h < height; h++) {
		    for (int w = 0; w < width; w++) {
			TileButton button = new TileButton();
			button.setBackground(memoryComp.fillButton(gameBoard, h, w));
			button.setOpaque(true);
		 	memoryPanel.add(button);
			addButton(button);
		    }
		}

 */