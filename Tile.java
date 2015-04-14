package se.liu.ida.linbe810.tddd78.memory;

import java.awt.Color;

public class Tile
{
    private final static int SIZE = 100;
    TileTypes type;
    Color frontsideColour;
    Color backsideColour;
    boolean isVisible;

    public Tile(TileTypes type, Color frontsideColour, Color backsideColour) {
        this.type = type;
        this.frontsideColour = frontsideColour;
        this.backsideColour = backsideColour;
    }

    public static int getTileSize() {
        return SIZE;
    }

    public TileTypes getTileType(){
        return type;
    }

    public Color getFrontsideColour() {
        return frontsideColour;
    }

    public Color getBacksideColour() {
        return backsideColour;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) return false;

        Tile that = (Tile) other;

        return this.type == that.type &&
               this.frontsideColour.equals(that.frontsideColour) &&
               this.backsideColour.equals(that.backsideColour);
    }
}
