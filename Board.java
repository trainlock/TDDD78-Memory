package se.liu.ida.linbe810.tddd78.memory;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board
{
    public Tile[][] tiles;
    private int height, width;
    private List<Tile> listOfTiles;
    private List<Color> listOfColours;
    private List<TileTypes> listOfTypes;

    public Board(int height, int width) {
	this.height = height;
	this.width = width;

	setListOfTypes();
	setListOfColours();
	createTiles();

	if (checkBoardSize()) {

	    Collections.shuffle(listOfTiles);

	    this.tiles = new Tile[height][width];
	    for (int h = 0; h < height; h++) {
		for (int w = 0; w < width; w++) {
		    tiles[h][w] = getRndTile();
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

    public Tile getTile(int height, int width) {
	return tiles[height][width];
    }

    // Gör om till en generics
    public int getListSize(List list){
	return list.size();
    }


    public Color getColourFromList() {
	int size = getListSize(listOfColours);
	Color colour = listOfColours.get(0);
	System.out.println("One Colour: " + colour);
	listOfColours.remove(0);
	return colour;
    }

    public void createTiles() {
	listOfTiles = new ArrayList<Tile>();
	TileTypes type = getTileTypeFromList();
	for (Color colour : listOfColours) {
	    Tile tile1 = new Tile(type, colour, Color.GRAY);
	    Tile tile2 = new Tile(type, colour, Color.GRAY);
	    listOfTiles.add(tile1);
	    listOfTiles.add(tile2);
	}
	System.out.println("List: " + listOfTiles);
    }

    public void setListOfTypes() {
	listOfTypes = new ArrayList<TileTypes>();
	for (TileTypes tileType : TileTypes.values()) {
	    listOfTypes.add(tileType);
	}
    }
    public TileTypes getTileTypeFromList() {
	int size = getListSize(listOfTypes);
	TileTypes type = null;
	for (int i = 0; i < size; i++) {
	    type = listOfTypes.get(i);
	}
	return type;
    }

    public void setListOfColours() {
	listOfColours = new ArrayList<Color>();
	for (TileColours tileColour : TileColours.values()) {
	    listOfColours.add(tileColour.getColour());
	}
    }

    public Tile getRndTile() {
	System.out.println("Random list thingy " + listOfTiles);
	Tile myTile = null;
	if (listOfTiles.size() != 0) {
	    myTile = listOfTiles.get(0);
	    listOfTiles.remove(0);
	}
	System.out.println("myTile: " + myTile);
	return myTile;
    }

    public boolean isSameTile(Tile t1, Tile t2) {
	return t1.equals(t2);
    }

    public boolean checkBoardSize() {
	if ((height * width) % 2 == 0 ) {
	    return true;
	}
	return false;
    }
}


    //private List<TileTypes> temp;
    //private List<TileTypes> myList;

/**
 * final List<TileTypes> tileList = new ArrayList<TileTypes>(Arrays.asList(TileTypes.values()));
tileList.remove(TileTypes.BACKSIDE);
tileList.remove(TileTypes.OUTSIDE);
temp = new ArrayList<TileTypes>(tileList);
myList = new ArrayList<TileTypes>(tileList);
myList.addAll(temp);
 */

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