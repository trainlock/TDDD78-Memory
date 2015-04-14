package se.liu.ida.linbe810.tddd78.memory;

import java.awt.Color;

public enum TileColours
{
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    RED(Color.RED),
    ORANGE(Color.ORANGE),
    YELLOW(Color.YELLOW),
    CYAN(Color.CYAN),
    PINK(Color.PINK),
    MAGENTA(Color.MAGENTA);

    private Color colour;

     TileColours(final Color colour) {
	 this.colour = colour;
     }

    public Color getColour() {
        return colour;
    }
}
