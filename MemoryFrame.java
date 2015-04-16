package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


public class MemoryFrame extends JFrame implements MouseListener
{
    private JFrame frame;
    private TileActionManager tileManager;
    private Counter clock;
    public boolean boardEnabled;
    private boolean isPlaying = true;
    private boolean isAllSame;

    public MemoryFrame(Board gameBoard) {
	super("MyMemories");
	// Kommer behöva var mycket större än det är nu!

	final MemoryComponent memoryComp = new MemoryComponent(gameBoard);
	tileManager = new TileActionManager(memoryComp, gameBoard);

	this.frame = new JFrame("Memory");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenus();

	frame.setLayout(new BorderLayout());
	frame.getContentPane().add(memoryComp, BorderLayout.CENTER);
	frame.getContentPane().addMouseListener(this);
	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);

	while(isPlaying) {
	    Counter clock = new Counter();

	    isAllSame = tileManager.isAllSameTile();
	    if (isAllSame) {
	   	    JOptionPane.showMessageDialog(this, "You have beaten the game! Congrats CHAMP!");
		isPlaying = false;
	   	}

	 }
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
		if (optionChosen == JOptionPane.NO_OPTION) {
		    System.exit(0);
		}
	    }
	});
	quitButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"No, I'm on a roll!", "Yes, unfortunately"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Are you really sure you want to do that?",
								"No!! You can't do this! Why?!",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
		if (optionChosen == JOptionPane.NO_OPTION) {
		    System.exit(0);
		}
		else {
		    JOptionPane.showMessageDialog(frame.getParent(), "That's my boy!!");
		}
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

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {
	this.boardEnabled = tileManager.isBoardEnabled();
	if (boardEnabled){
	    // Har en state på mouseEvent som säger om man kan änvända eller ej.
	    int xCoord = e.getX();
	    int yCoord = e.getY();
	    // VIKTIGT BYT METODNAMN!!!!
	    tileManager.click(xCoord, yCoord);
	}
    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }
}

	//int height = gameBoard.getHeight();
	//int width = gameBoard.getWidth();
	//tileManager.setStartTileValue(height, width);