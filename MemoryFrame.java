package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


public class MemoryFrame extends JFrame implements MouseListener
{
    private Board gameBoard;
    private JFrame frame;
    private MemoryComponent memoryComp;
    private Tiles t1, t2;
    private int x, y;
    private boolean[][] isTurnedUp;



    public MemoryFrame(final Board gameBoard) {
	super("MyMemories");
	this.gameBoard = gameBoard;
	memoryComp = new MemoryComponent(gameBoard);
	this.frame = new JFrame("Memory");

	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();

	setStartTileValue(height, width);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenus();

	frame.setLayout(new BorderLayout());
	frame.getContentPane().add(memoryComp, BorderLayout.CENTER);
	frame.getContentPane().addMouseListener(this);
	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);


	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {
	    }
	};

	final Timer clockTimer = new Timer(500, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    public void setStartTileValue(int height, int width) {
	isTurnedUp = new boolean[height][width];
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		isTurnedUp[h][w] = true;
		//System.out.println(h+" "+w+" "+isTurnedUp[h][w]);

		System.out.println("Hej");
	    }
	}
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

    private void resetTile(){
	t1 = null;
	t2 = null;
    }

    public void fillSameTile(int curY, int curX){
	memoryComp.fillBacksideOfTile(y, x);
	memoryComp.fillBacksideOfTile(curY, curX);
    }

    public void turnTile(int curY, int curX) {
	if (gameBoard.isSameTile(t1, t2) && isTurnedUp[curY][curX] && isTurnedUp[y][x]) {
	    fillSameTile(curY, curX);
	    // Fyller två tiles som är av samma sort med ljuwgrå färg.
	}
	else {
	    if (isTurnedUp[y][x] && isTurnedUp[curY][curX]) {
		// Om isTurnedUp == false, (nedvänd)
		// för första och andra tiles -->
		// fyller grått.
		memoryComp.fillCurTile(y, x);
		memoryComp.fillCurTile(curY, curX);
		// Sätter att tilen är isTurnedUp == true, (uppvänd)
		isTurnedUp[y][x] = false;
		isTurnedUp[curY][curX] = false;
	    }
	    else {
		// Kommer aldrig in i else-satsen.
		System.out.println("Bananer");
		// Om de inte är isTurnedUp == true, (uppvänd)
		// för första och andra tiles -->
		// fyller med färg.
		memoryComp.fillTile(y, x);
		memoryComp.fillTile(curY, curX);
		isTurnedUp[y][x] = true;
		isTurnedUp[curY][curX] = true;
	    }
	}
    }

    // VIKTIGT BYT METODNAMN!!!!
    public void yolo(int xCoord, int yCoord){
	// VIKTIGT BYT METODNAMN!!!!
	int size = memoryComp.getSquareSize();
	int curX = xCoord/size;
	int curY = yCoord/size;
	if (t1 == null) {
	    t1 = gameBoard.getTile(curY, curX);
	    // Hämtar den första tilen som är tryckt.
	    x = curX;
	    y = curY;
	}
	else if (t2 == null) {
	    if (curX != x || curY != y) {
		// Om det inte är samma tiles gör den detta.
		t2 = gameBoard.getTile(curY, curX);
		// Hämtar den andra tilen som är tryckt.
		if(isTurnedUp[curY][curX]) {
		    turnTile(curY, curX);
		}
		else if(!isTurnedUp[curY][curX]){
		    turnTile(curY, curX);
		}

		// Vänder två valda tiles.
		resetTile();
		// Sätter valda tiles till null.
	    }
	    else {
		System.out.println("DON'T PRESS THE SAME TILE!");
		JOptionPane.showMessageDialog(this, "Do not press the same tile twice, retard!");
		resetTile();
	    }
	}
	else {
	    //Vad som händer när det inte är ett matchande par.
	    //De ska vändas tillbaka.
	}
    }

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {
	int xCoord = e.getX();
	int yCoord = e.getY();
	// VIKTIGT BYT METODNAMN!!!!
	yolo(xCoord, yCoord);
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