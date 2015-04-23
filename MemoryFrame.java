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
    private Counter clock = new Counter();
    private BoardPanel myBoard;
    public Board gameBoard;
    public boolean boardEnabled;
    private boolean isPlaying;
    private boolean isAllSame;
    private int PANEL_HEIGHT;
    private int counter = 0;
    final MemoryComponent memoryComp;


    public MemoryFrame(Board gameBoard) {
	super("MyMemories");
	this.gameBoard = gameBoard;
	this.isPlaying = true;

	// Kommer behöva var mycket större än det är nu!
	memoryComp = new MemoryComponent(gameBoard);
	tileManager = new TileActionManager(memoryComp, gameBoard);

	this.myBoard = new BoardPanel(gameBoard, memoryComp);

	this.frame = new JFrame("Memory");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenus();

	BorderLayout b = new BorderLayout();
	frame.setLayout(b);

	frame.add(clock, BorderLayout.NORTH);
	frame.getContentPane().add(myBoard, BorderLayout.SOUTH);
	frame.getContentPane().addMouseListener(this);

	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);

	run();
    }

    /**
    void restartGame() {
	remove(myBoard);
	BoardPanel newBoard = new BoardPanel(new Board(gameBoard.getHeight(), gameBoard.getWidth()), memoryComp);
	frame.add(newBoard);
	revalidate();
    }
     */

    private void run() {
	while(isPlaying) {

	    isAllSame = tileManager.isAllSameTile();

	    if (isAllSame) {
		clock.stopTimer();
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
		Object[] options = {"Yes", "No"};
		int optionChosen = JOptionPane.showOptionDialog(frame.getParent(),
								"Would you like to start a new game?",
								"New game",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION) {
		    /**
		    gameBoard.restart();
		    tileManager.resetTiles(gameBoard);
		    memoryComp.repaint();
		     */
		}
	    }
	});
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
								options[1]);
		if (optionChosen == JOptionPane.YES_OPTION) {
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
	this.PANEL_HEIGHT = clock.getHeight();
	if (boardEnabled){
	    // Har en state på mouseEvent som säger om man kan använda eller ej.
	    int xCoord = e.getX();
	    int yCoord = e.getY();
	    // FIXA SÅ ATT DEN INTE KÄNNER AV MUSKLICKNINGAR NÄR DEN ÄR PÅ PANEL
	    if (yCoord - PANEL_HEIGHT >= 0) {
		tileManager.click(xCoord, (yCoord - PANEL_HEIGHT));
		isPlaying = true;
		if(isPlaying && counter == 0){
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