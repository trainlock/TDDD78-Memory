package se.liu.ida.linbe810.tddd78.memory;

import java.awt.Color;

public class Tile
{
    private final static int TILE_SIZE = 100;
    private TileTypes type;
    private TileState state;
    //private Color frontsideColour;
    private Color backsideColour;

    public Tile(TileTypes type, Color backsideColour, TileState state) {
        this.type = type;
        //this.frontsideColour = frontsideColour;
        this.backsideColour = backsideColour;
        this.state = state;
    }

    public final static int getTileSize() {
        return TILE_SIZE;
    }

// --Commented out by Inspection START (2015-04-28 10:36):
//    public se.liu.ida.linbe810.tddd78.memory.TileTypes getTileType(){
//        return type;
//    }
// --Commented out by Inspection STOP (2015-04-28 10:36)

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public TileTypes getTileType() {
        return type;
    }

    public Color getBacksideColour() {
        return backsideColour;
    }

    /**
     Checks whether this class is equal to another
     * @param other The other class which is compare to this class
     * @return True if the classes are equal, else returns false
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) return false;

        Tile that = (Tile) other;

        return this.type == that.type &&
               //this.frontsideColour.equals(that.frontsideColour) &&
               this.backsideColour.equals(that.backsideColour);
    }
}
