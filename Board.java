package se.liu.ida.linbe810.tddd78.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board
{
    private Tiles[][] square;
    private int height, width;
    private List<Tiles> tileList;
    private List <Tiles> temp;
    private List <Tiles> myList;

    public Board(int height, int width) {
	this.height = height;
	this.width = width;

	this.tileList = new ArrayList<Tiles>(Arrays.asList(Tiles.values()));
	tileList.remove(Tiles.BACKSIDE);
	tileList.remove(Tiles.OUTSIDE);
	temp = new ArrayList<Tiles>(tileList);
	myList = new ArrayList<Tiles>(tileList);
	myList.addAll(temp);
	Collections.shuffle(myList);

	this.square = new Tiles[height][width];
	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		square[h][w] = getRndTile();
		/**if (h == 0 || w == 0 || h == height - 1 || w == width - 1) {
		    square[h][w] = Tiles.OUTSIDE;
		} else {
		    square[h][w] = getRndTile();
		}
		 */
	    }
	}
    }


    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public Tiles getTile(int height, int width) {
	return square[height][width];
    }

    public Tiles getCoord(Tiles A) {
	return Tiles.A;
    }

    public int nrOfTiles(){
	return myList.size();
    }

    public Tiles getRndTile(){
	Tiles myTile = myList.get(0);
	myList.remove(0);
	return myTile;
	}
    }

/**
    public boolean canBeSelected() {
	boolean selectable = false;
	Tiles tile = getRndTile();
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


Tiles t1 = tileList.get(tileIndex);
	Tiles t2 = t1;
	nrOfElem.add(t1);
	nrOfElem.add(t2);
Collections.shuffle(nrOfElem);
for (int j = 0; j < tileList.size(); j++) {
*/