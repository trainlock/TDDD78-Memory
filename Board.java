package se.liu.ida.linbe810.tddd78.memory;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Board
{
    private Tile[][] tiles = null;
    private int height, width;
    private List<Tile> listOfTiles = null;
    private List<Color> listOfColours = null;
    private List<TileTypes> listOfTypes = null;

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
    public int getListSize(Collection list){
	return list.size();
    }

    public void createTiles() {
	listOfTiles = new ArrayList<Tile>();
	TileTypes type = getTileTypeFromList();
	for (Color colour : listOfColours) {
	    Tile tile1 = new Tile(type, colour, Color.GRAY, TileState.IS_UP);
	    Tile tile2 = new Tile(type, colour, Color.GRAY, TileState.IS_UP);
	    listOfTiles.add(tile1);
	    listOfTiles.add(tile2);
	}
    }

    public void setListOfTypes() {
	listOfTypes = new ArrayList<TileTypes>();
	Collections.addAll(listOfTypes, TileTypes.values());
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
	Tile myTile = null;
	if (!listOfTiles.isEmpty()) {
	    myTile = listOfTiles.get(0);
	    listOfTiles.remove(0);
	}
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

    public void restart(){
	Collections.shuffle(listOfTiles);
	this.tiles = new Tile[height][width];
 	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
  		tiles[h][w] = getRndTile();
	    }
 	}
    }

}