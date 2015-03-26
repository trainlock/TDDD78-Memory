package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TileActionManager
{
    private Board gameBoard;
    private MemoryComponent memoryComp;
    private Timer clockTimer;
    public TileState[][] isTurnedUp;
    private int x, y, copiedX, copiedY, curX, curY;
    private Tiles t1, t2;
    public boolean boardEnabled = true;
    public static final int TIME = 2000;

    public TileActionManager(MemoryComponent memoryComp, Board gameBoard) {
	this.memoryComp = memoryComp;
	this.gameBoard = gameBoard;
    }

    public boolean isAllSameTile(){
	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();
	int counter = 0;
	for (int h = 0; h < height ; h++) {
	    for (int w = 0; w < width; w++) {
		if(isTurnedUp[h][w] == TileState.IS_SAME_TILE){
		    counter++;
		}
	    }
	}
	if(counter == (height * width)) {
	    //System.out.println("Congrats Champ!");
	    return true;
	}
	return false;
    }

    public void setXYValues(int x, int y) {
	this.copiedX = x;
	this.copiedY = y;
    }

    public void setBoardEnabled(boolean boardEnabled) {
	this.boardEnabled = boardEnabled;
    }

    public boolean isBoardEnabled() {
	return boardEnabled;
    }

    public void runTimer(){
	// Ta in final int? Be labbass att kolla på koden och se om vi gör någorlunda rätt
	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {

		if (isTurnedUp[copiedY][copiedX] == TileState.IS_DOWN && isTurnedUp[y][x] == TileState.IS_DOWN) {
		    // whenDown();
		    // ska ignorera allt man gör på spelplanen under ticken.
			System.out.println("Board Enabled - state: " + boardEnabled);
			isTurnedUp[copiedY][copiedX] = TileState.IS_UP;
			isTurnedUp[curY][curX] = TileState.IS_UP;
			memoryComp.fillTile(copiedY, copiedX);
			memoryComp.fillTile(curY, curX);
			clockTimer.stop();
			setBoardEnabled(true);
		}
		else{
		    clockTimer.stop();
		    setBoardEnabled(true);
		    System.out.println(boardEnabled + " after runTimer");
		}
	    }
	};

	clockTimer = new Timer(TIME, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    public void setStartTileValue(int height, int width) {
	isTurnedUp = new TileState[height][width];
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		isTurnedUp[h][w] = TileState.IS_UP;
	    }
	}
    }

    public void resetTile(){
	t1 = null;
	t2 = null;
    }

    public void fillSameTile(int curY, int curX){
	memoryComp.fillBacksideOfTile(y, x);
	memoryComp.fillBacksideOfTile(curY, curX);
    }

    public void turnTile(int curY, int curX) {
	if (gameBoard.isSameTile(t1, t2) && isTurnedUp[curY][curX] == TileState.IS_UP && isTurnedUp[y][x] == TileState.IS_UP) {
	    fillSameTile(curY, curX);
	    // Fyller två tiles som är av samma sort med ljusgrå färg.
	    isTurnedUp[curY][curX] = TileState.IS_SAME_TILE;
	    isTurnedUp[y][x] = TileState.IS_SAME_TILE;
	    // sätter så att tilen inte kan väljas igen.
	}
	else {
	    if (isTurnedUp[y][x] == TileState.IS_UP && isTurnedUp[curY][curX] == TileState.IS_UP) {
		// Om isTurnedUp == false, (nedvänd)
		// för första och andra tiles -->
		// fyller grått.
		memoryComp.fillCurTile(y, x);
		memoryComp.fillCurTile(curY, curX);
		// Sätter att tilen är isTurnedUp == true, (uppvänd)
		isTurnedUp[y][x] = TileState.IS_DOWN;
		isTurnedUp[curY][curX] = TileState.IS_DOWN;
		// Timern ska automatiskt sätta IS_DOWN till IS_UP
	    }
	    else if (isTurnedUp[curY][curX] == TileState.IS_SAME_TILE || isTurnedUp[y][x] == TileState.IS_SAME_TILE ||
		     isTurnedUp[y][x] == TileState.IS_UP && isTurnedUp[curY][curX] == TileState.IS_DOWN ||
		     isTurnedUp[curY][curX] == TileState.IS_UP && isTurnedUp[y][x] == TileState.IS_DOWN) {
		// Borde tillhöra felhanteringen och Exceptions
		// Spelplanen ska ej komma åt dessa brickor!
	    }
	    else {
		// Om de inte är isTurnedUp == true, (uppvänd)
		// för första och andra tiles -->
		// fyller med färg.
		memoryComp.fillTile(y, x);
		memoryComp.fillTile(curY, curX);
		isTurnedUp[y][x] = TileState.IS_UP;
		isTurnedUp[curY][curX] = TileState.IS_UP;
	    }
	}
    }

        // VIKTIGT BYT METODNAMN!!!!
    public void yolo(int xCoord, int yCoord) {
	// VIKTIGT BYT METODNAMN!!!!
	int size = memoryComp.getSquareSize();
	// size tar inte med SPACE! Därför blir det mysko med mellanrummen
	this.curX = xCoord / size;
	this.curY = yCoord / size;

	if (t1 == null) {
	    t1 = gameBoard.getTile(curY, curX);
	    // Hämtar den första tilen som är tryckt.
	    x = curX;
	    y = curY;
	    setXYValues(x, y);
	}
	else if (t2 == null) {
	    if (curX != x || curY != y) {
		// Om det inte är samma tiles gör den detta.
		t2 = gameBoard.getTile(curY, curX);
		// Hämtar den andra tilen som är tryckt.
		if (isTurnedUp[curY][curX] == TileState.IS_UP || isTurnedUp[curY][curX] == TileState.IS_DOWN) {
		    turnTile(curY, curX);
		}
		// Vänder två valda tiles.
		resetTile();
		setBoardEnabled(false);
		// Sätter valda tiles till null.
		runTimer();
		// Timer börjar sin nedräkning
		if(isAllSameTile()){

		}
		// Kollar om alla är same.
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
