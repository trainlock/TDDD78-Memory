package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public class TileActionManager
{
    private Board gameBoard;
    private MemoryComponent memoryComp;
    private Timer clockTimer = null;
    private int t2X, t2Y, t1X, t1Y;
    private Tile t1 = null, t2 = null;
    private boolean boardEnabled = true;
    public static final int TIME = 2000;
    private Logger logger;

    public TileActionManager(MemoryComponent memoryComp, Board gameBoard) {
	this.memoryComp = memoryComp;
	this.gameBoard = gameBoard;
	logger = Logger.getLogger(TileActionManager.class.getName());
    }

    public void setBoardEnabled(boolean boardEnabled) {
	this.boardEnabled = boardEnabled;
    }

    public boolean isBoardEnabled() {
	return boardEnabled;
    }

    public boolean isAllSameTile() {
	int counter = 0;
	int height = gameBoard.getHeight();
 	// height == row == t2Y
	int width = gameBoard.getWidth();
 	// width == column == t2X

	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {

		Tile currentTile = gameBoard.getTile(h , w);

		if (currentTile.getState() == TileState.IS_SAME_TILE) {
		    counter++;
		}
	    }
	}
	if (counter == (height * width )) {
	    return true;
	}
	return false;
	//logger.info("Successful control of Tiles!");
    }

    public void runTimer(){
	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {

		Tile curTile1 = gameBoard.getTile(t1Y, t1X);
		Tile curTile2 = gameBoard.getTile(t2Y, t2X);

		curTile1.setState(TileState.IS_UP);
  		curTile2.setState(TileState.IS_UP);
  		memoryComp.fillTile(curTile1.getState(), t1Y, t1X);
  		memoryComp.fillTile(curTile2.getState(), t2Y, t2X);
  		clockTimer.stop();
  		setBoardEnabled(true);
	    }
	};

	clockTimer = new Timer(TIME, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    public void resetTile(){
	t1 = null;
	t2 = null;
    }


    public void resetTiles(Board myBoard) {
	for (int h = 0; h < myBoard.getHeight(); h++) {
	    for (int w = 0; w < myBoard.getWidth(); w++) {
		Tile currentTile = myBoard.getTile(h , w);
		currentTile.setState(TileState.IS_UP);
	    }
	}
    }



    /**
     * Operation which calls a methods to change the color of the se.liu.ida.linbe810.tddd78.memory.Tile
     * depending on which state is has. It also check to see which if
     * two Tiles should be repainted.
     *
     * @param t1Y	current se.liu.ida.linbe810.tddd78.memory.Tile y-position on the board
     * @param t1X	current se.liu.ida.linbe810.tddd78.memory.Tile x-position on the board
     */
    public void turnTile(int t1Y, int t1X) {
	if (gameBoard.isSameTile(t1, t2) && t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_UP) {
	    t1.setState(TileState.IS_SAME_TILE);
	    t2.setState(TileState.IS_SAME_TILE);
	    memoryComp.fillTile(t1.getState(), t1Y, t1X);
	    memoryComp.fillTile(t2.getState(), t2Y, t2X);
	}
	else {
	    if (t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_UP) {
		t1.setState(TileState.IS_DOWN);
  		t2.setState(TileState.IS_DOWN);
		memoryComp.fillTile(t2.getState(), t2Y, t2X);
		memoryComp.fillTile(t1.getState(), t1Y, t1X);
	    }
	    if(t1.getState() == TileState.IS_DOWN && t2.getState() == TileState.IS_DOWN){
		this.boardEnabled = false;
		runTimer();
	    }
	    else if (!(t1.getState() == TileState.IS_SAME_TILE || t2.getState() == TileState.IS_SAME_TILE ||
		     t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_DOWN ||
		     t1.getState() == TileState.IS_DOWN && t2.getState() == TileState.IS_UP)) {

		memoryComp.fillTile(t2.getState(), t2Y, t2X);
		memoryComp.fillTile(t1.getState(), t1Y, t1X);
		t1.setState(TileState.IS_UP);
  		t2.setState(TileState.IS_UP);
	    }
	}
    }

    /**
     * Vad metoden gör!
     *
     * @param xCoord	the x-coordinate of the mouse where it is pressed.
     * @param yCoord	the y-coordinate of the mouse where it is pressed
     */
    public void assignCoordsToTile(int xCoord, int yCoord) {
	int size = Tile.getTileSize();

	this.t1X = xCoord / size;
	this.t1Y = yCoord / size;

	if (t1 == null) {
	    t1 = gameBoard.getTile(t1Y, t1X);
	    t2X = t1X;
	    t2Y = t1Y;
	}
	else if (t2 == null) {
	    if (t1X != t2X || t1Y != t2Y) {
		t2 = gameBoard.getTile(t1Y, t1X);
		if (t2.getState() == TileState.IS_UP || t2.getState() == TileState.IS_DOWN) {
		    turnTile(t1Y, t1X);
		}
		resetTile();
	    }
	    else {
		System.out.println("Please don't press the same tile twice.");
		resetTile();
	    }
	}
    }
}