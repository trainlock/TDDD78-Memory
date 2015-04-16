package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TileActionManager
{
    private Board gameBoard;
    private MemoryComponent memoryComp;
    private Timer clockTimer;
    public TileState[][] isTurnedUp;
    private int t2X, t2Y, copiedX, copiedY, t1X, t1Y;
    private Tile t1, t2;
    public boolean boardEnabled = true;
    public static final int TIME = 100;

    public TileActionManager(MemoryComponent memoryComp, Board gameBoard) {
	this.memoryComp = memoryComp;
	this.gameBoard = gameBoard;
    }

    public void setXYValues(int t2X, int t2Y) {
	this.copiedX = t2X;
	this.copiedY = t2Y;
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

		if (curTile1.getState() == TileState.IS_DOWN && curTile2.getState() == TileState.IS_DOWN) {
		    // whenDown();
		    // ska ignorera allt man gör på spelplanen under ticken.
		    curTile1.setState(TileState.IS_UP);
		    curTile2.setState(TileState.IS_UP);
		    memoryComp.fillTile(curTile1.getState(), t1Y, t1X);
		    memoryComp.fillTile(curTile2.getState(), t2Y, t2X);
		    clockTimer.stop();
		    setBoardEnabled(true);
		    //isTurnedUp[copiedY][copiedX] = TileState.IS_UP;
			//isTurnedUp[t1Y][t1X] = TileState.IS_UP;
		}
		else{
		    clockTimer.stop();
		    setBoardEnabled(true);
		    setBoardEnabled(true);
		    System.out.println(boardEnabled + " after runTimer");
		    isAllSameTile();
		}
	    }
	};

	clockTimer = new Timer(TIME, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }
/**
    public void setStartTileValue(int height, int width) {
	isTurnedUp = new TileState[height][width];
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		isTurnedUp[h][w] = TileState.IS_UP;
	    }
	}
    }
 */

    public void resetTile(){
	t1 = null;
	t2 = null;
    }
/**
    public void fillSameTile(int t1Y, int t1X){
	memoryComp.fillTile(t2Y, t2X);
	memoryComp.fillTile(t1Y, t1X);
    }
 */

    public void turnTile(int t1Y, int t1X) {
	if (gameBoard.isSameTile(t1, t2) && t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_UP) {
	    t1.setState(TileState.IS_SAME_TILE);
	    t2.setState(TileState.IS_SAME_TILE);
	    memoryComp.fillTile(t1.getState(), t1Y, t1X);
	    memoryComp.fillTile(t2.getState(), t2Y, t2X);
	    // Fyller två tiles som är av samma sort med ljusgrå färg.
	    // sätter så att tilen inte kan väljas igen.
	}
	else {
	    if (t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_UP) {
		// Sätter att tilen är isTurnedUp == true, (uppvänd)
		t1.setState(TileState.IS_DOWN);
  		t2.setState(TileState.IS_DOWN);
		// Om isTurnedUp == false, (nedvänd)
		// för första och andra tiles -->
		// fyller grått.
		memoryComp.fillTile(t2.getState(), t2Y, t2X);
		memoryComp.fillTile(t1.getState(), t1Y, t1X);
		// Timern ska automatiskt sätta IS_DOWN till IS_UP
	    }
	    else if (t1.getState() == TileState.IS_SAME_TILE || t2.getState() == TileState.IS_SAME_TILE ||
		     t1.getState() == TileState.IS_UP && t2.getState() == TileState.IS_DOWN ||
		     t1.getState() == TileState.IS_DOWN && t2.getState() == TileState.IS_UP) {
		// Borde tillhöra felhanteringen och Exceptions
		// Spelplanen ska ej komma åt dessa brickor!
	    }
	    else {
		// Om de inte är isTurnedUp == true, (uppvänd)
		// för första och andra tiles -->
		// fyller med färg.
		memoryComp.fillTile(t2.getState(), t2Y, t2X);
		memoryComp.fillTile(t1.getState(), t1Y, t1X);
		t1.setState(TileState.IS_UP);
  		t2.setState(TileState.IS_UP);
	    }
	}
    }

        // VIKTIGT BYT METODNAMN!!!!
    public void yolo(int xCoord, int yCoord) {
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
	    setXYValues(t2X, t2Y);
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
		setBoardEnabled(false);
		// Sätter valda tiles till null.
		runTimer();
		// Timer börjar sin nedräkning
	    }
	    else {
		System.out.println("DON'T PRESS THE SAME TILE!");
		// ÄNDRA TEXTEN RETARD!!!
		resetTile();
	    }
	}
	else {
	    //Vad som händer när det inte är ett matchande par.
	    //De ska vändas tillbaka.
	}
    }
}
