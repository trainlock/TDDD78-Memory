package se.liu.ida.linbe810.tddd78.memory;

import Highscores.HighscoreManager;
import Highscores.HighscorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;


public class MemoryFrame extends JFrame implements MouseListener
{
    private JFrame frame;
    private TileActionManager tileManager;
    private Counter clock = new Counter();
    private Board gameBoard;
    private boolean isPlaying;
    private int counter = 0;
    private Logger logger;

    // for the highscore.
    private HighscorePanel hsPanel = new HighscorePanel();


    public MemoryFrame(Board gameBoard) {
	super("MyMemories");
	this.gameBoard = gameBoard;
	this.isPlaying = true;
	logger = Logger.getLogger(MemoryFrame.class.getName());

	// Kommer behöva var mycket större än det är nu!
	final MemoryComponent memoryComp = new MemoryComponent(gameBoard);
	tileManager = new TileActionManager(memoryComp, gameBoard);

	final BoardPanel myBoard = new BoardPanel(memoryComp);

	this.frame = new JFrame("Memory");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenus();

	LayoutManager bL = new BorderLayout();
	frame.setLayout(bL);

	frame.add(clock, BorderLayout.NORTH);
	frame.add(hsPanel, BorderLayout.EAST);
	frame.getContentPane().add(myBoard, BorderLayout.CENTER);
	frame.getContentPane().addMouseListener(this);

	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);

	run();
    }

    private void run() {
	while(isPlaying) {

	    final boolean isAllSame = tileManager.isAllSameTile();

	    if (isAllSame) {
		clock.stopTimer();
		final String name = JOptionPane.showInputDialog(this, "You have beaten the game! Enter your name: ");

		// Generates the highscore list.
		HighscoreManager.createHighscoreManager().addScore(name, clock.getTime());
		logger.info("Game finished!");

		isPlaying = false;

		Object[] options = {"New Game", "Quit"};
			int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
									"Would you like to start a new game or quit?",
									null,
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,
									options,
									options[0]);
			if(optionChosen == 0) {
			    gameBoard = new Board(4,4);
			    new MemoryFrame(gameBoard);
			}

			else if(optionChosen == 1){
			    System.exit(0);
			}

	    		}
	   	}

	 }


    private void createMenus() {
	//JButton newGameButton = new JButton("New Game");
	JButton quitButton = new JButton("Quit");

	/**
	newGameButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes", "No"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Would you like to start a new game?",
								"New game",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[0]);
		if (optionChosen == JOptionPane.YES_OPTION) {
		    // KRASCHAR!!!
		    // frame.dispose();
		    isPlaying = false;
		    gameBoard = new se.liu.ida.linbe810.tddd78.memory.Board(4,4);
		    System.out.println("new");
		    new se.liu.ida.linbe810.tddd78.memory.MemoryFrame(gameBoard);
		    System.out.println("game");
		    }
		else if (optionChosen == JOptionPane.NO_OPTION) {
		    System.exit(0);
		}
	    }
	});
	 */

	quitButton.setAction(new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
		Object[] options = {"Yes", "No"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Would you like to quit the game?",
								"Quit",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[0]);
		if (optionChosen == JOptionPane.YES_OPTION) {
		    System.exit(0);
		}
		else {
		    JOptionPane.showMessageDialog(frame.getParent(), "That's my boy!!");
		}
	    }
	});

	//newGameButton.setText("New Game");
	quitButton.setText("Quit");

	JMenuBar menuBar = new JMenuBar();
	//menuBar.add(newGameButton);
	menuBar.add(Box.createHorizontalGlue());
	menuBar.add(quitButton);
	frame.setJMenuBar(menuBar);
    }

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {
	final boolean boardEnabled = tileManager.isBoardEnabled();
	final int panelHeight = clock.getHeight();
	if (boardEnabled){
	    // Har en state på mouseEvent som säger om man kan använda eller ej.
	    int xCoord = e.getX();
	    int yCoord = e.getY();

	    if (yCoord - panelHeight >= 0) {
		tileManager.assignCoordsToTile(xCoord, (yCoord - panelHeight));
		isPlaying = true;
		if(counter == 0){
		    counter++;
		}
	    }
	}
    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }
}