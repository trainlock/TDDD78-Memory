package se.liu.ida.linbe810.tddd78.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board
{
    public TileTypes[][] square;
    private int height, width;
    private List<TileTypes> temp;
    private List<TileTypes> myList;

    public Board(int height, int width) {
	this.height = height;
	this.width = width;
	if (checkBoardSize()) {
	    final List<TileTypes> tileList = new ArrayList<TileTypes>(Arrays.asList(TileTypes.values()));
	    tileList.remove(TileTypes.BACKSIDE);
	    tileList.remove(TileTypes.OUTSIDE);
	    temp = new ArrayList<TileTypes>(tileList);
	    myList = new ArrayList<TileTypes>(tileList);
	    myList.addAll(temp);
	    Collections.shuffle(myList);

	    this.square = new TileTypes[height][width];
	    for (int h = 0; h < height; h++) {
		for (int w = 0; w < width; w++) {
		    square[h][w] = getRndTile();
	    	}
	    }
	}
	else {
	    // FELHANTERING!!!
	    System.out.println("Incorrect size of Board!");
	}
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public TileTypes getTile(int height, int width) {
	return square[height][width];
    }

    public TileTypes getRndTile() {
	TileTypes myTile = myList.get(0);
	myList.remove(0);
	return myTile;
    }

    public boolean isSameTile(TileTypes t1, TileTypes t2) {
	return t1.equals(t2);
    }

    public boolean checkBoardSize() {
	if ((height * width) % 2 == 0 ) {
	    return true;
	}
	return false;
    }
}



/**
    public boolean canBeSelected() {
	boolean selectable = false;
	TileTypes tile = getRndTile();
	if (getNrOfTile == 2) {
	    selectable = true;
	}
	return selectable;
    }
 */
    /**
    array = [a, a, b, b, c, c, d, d, e, e, f, f, g, g, h, h]
    [a, b, c, d, e, f, g, h]
     Blir [a, a][a, b][b, f] etc...
     */
/**
int tileIndex = r.nextInt(tileList.size() - 2);


TileTypes t1 = tileList.get(tileIndex);
	TileTypes t2 = t1;
	nrOfElem.add(t1);
	nrOfElem.add(t2);
Collections.shuffle(nrOfElem);
for (int j = 0; j < tileList.size(); j++) {
*/