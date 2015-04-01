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

    private Color color;

     TileColours(final Color color) {
	 this.color = color;
     }

    public Color getColour() {
	return color;
    }
}
