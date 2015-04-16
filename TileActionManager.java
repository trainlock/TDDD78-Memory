package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TileActionManager
{
    private Board gameBoard;
    private MemoryComponent memoryComp;
    private Timer clockTimer = null;
    private int t2X, t2Y, t1X, t1Y;
    private Tile t1, t2;
    private boolean boardEnabled = true;
    public static final int TIME = 2000;

    public TileActionManager(MemoryComponent memoryComp, Board gameBoard) {
	this.memoryComp = memoryComp;
	this.gameBoard = gameBoard;
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
    }

    public void runTimer(){
	// Ta in final int? Be labbass att kolla på koden och se om vi gör någorlunda rätt
	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {

		// Hur ska vi lyckas få med egna invariabler till actionPerformed
		// så man slipper upprepa kod??
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
		setBoardEnabled(false);
		runTimer();
	    }
	    else if (t1.getState() == TileState.IS_SAME_TILE || t2.getState() == TileState.IS_SAME_TILE ||
		     t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_DOWN ||
		     t1.getState() == TileState.IS_DOWN && t2.getState() == TileState.IS_UP) {
		// Borde tillhöra felhanteringen och Exceptions
		// Spelplanen ska ej komma åt dessa brickor!
	    }
	    else {
		memoryComp.fillTile(t2.getState(), t2Y, t2X);
		memoryComp.fillTile(t1.getState(), t1Y, t1X);
		t1.setState(TileState.IS_UP);
  		t2.setState(TileState.IS_UP);
	    }
	}
    }

        // VIKTIGT BYT METODNAMN!!!!
    public void click(int xCoord, int yCoord) {
	// VIKTIGT BYT METODNAMN!!!!
	int size = Tile.getTileSize();
	// size tar inte med SPACE! Därför blir det mysko med mellanrummen
	this.t1X = xCoord / size;
	this.t1Y = yCoord / size;

	if (t1 == null) {
	    t1 = gameBoard.getTile(t1Y, t1X);
	    // Hämtar den första tilen som är tryckt.
	    t2X = t1X;
	    t2Y = t1Y;
	}
	else if (t2 == null) {
	    if (t1X != t2X || t1Y != t2Y) {
		// Om det inte är samma tiles gör den detta.
		t2 = gameBoard.getTile(t1Y, t1X);
		// Hämtar den andra tilen som är tryckt.
		if (t2.getState() == TileState.IS_UP || t2.getState() == TileState.IS_DOWN) {
		    turnTile(t1Y, t1X);
		}
		// Vänder två valda tiles.
		resetTile();
		// Sätter valda tiles till null.
	    }
	    else {
		System.out.println("DON'T PRESS THE SAME TILE!");
		// ÄNDRA TEXTEN!!!
		resetTile();
	    }
	}
    }
}