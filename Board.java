package se.liu.ida.linbe810.tddd78.memory;

import java.awt.Color;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is used to create the gameboard.
 * Generates an dubblearray cointaining different types of Tiles in a random order.
 * The number of tiles depends on how many tileTypes that exist.
 */

public class Board
{
    private Tile[][] tiles = null;
    private int height, width;
    private List<Tile> listOfTiles = null;
    private List<Color> listOfColours = null;
    private List<TileTypes> listOfTypes = null;
    private Logger logger;

    public Board(int height, int width) {
	this.height = height;
	this.width = width;
	logger = Logger.getLogger(Board.class.getName());

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
	    logger.info("Incorrect construction of board!");
	}

	logger.info("Construction successful!");

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
    // Raw use of collection
    /**
    public int getListSize(Collection list){
	return list.size();
    }
     */

    public void createTiles() {
	listOfTiles = new ArrayList<Tile>();
	//se.liu.ida.linbe810.tddd78.memory.TileTypes type = getTileTypeFromList();
	for (TileTypes tileType : listOfTypes) {
	    Tile tile1 = new Tile(tileType, Color.GRAY, TileState.IS_UP);
	    Tile tile2 = new Tile(tileType, Color.GRAY, TileState.IS_UP);
	    listOfTiles.add(tile1);
	    listOfTiles.add(tile2);
	}
    }

    public void setListOfTypes() {
	listOfTypes = new ArrayList<TileTypes>();
	Collections.addAll(listOfTypes, TileTypes.values());
    }
    /**
    public se.liu.ida.linbe810.tddd78.memory.TileTypes getTileTypeFromList() {
	int size = getListSize(listOfTypes);
	se.liu.ida.linbe810.tddd78.memory.TileTypes type = null;
	for (int i = 0; i < size; i++) {
	    type = listOfTypes.get(i);
	}
	return type;
    }
     */

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
}