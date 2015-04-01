package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;

public class MemoryComponent extends JComponent
{
    private Board gameBoard;
    public final static int SQUARE_SIZE = Tile.getTileSize();
    public final static int SPACE = 10;

    public MemoryComponent(final Board gameBoard) {
	this.gameBoard = gameBoard;
    }

    @Override
    public Dimension getPreferredSize() {
	int width = SQUARE_SIZE * gameBoard.getWidth() + SPACE * gameBoard.getWidth();
	int height = SQUARE_SIZE * gameBoard.getHeight() + SPACE * gameBoard.getHeight();
        return new Dimension(width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	fillBoardColour(gameBoard, g);
    }

    public void fillBoardColour(Board gameBoard, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int height = gameBoard.getHeight();
        // height == row == y
       	int width = gameBoard.getWidth();
        // width == column == x

        System.out.println("ppppp");
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {

                Tile curTile = gameBoard.getTile(row, column);

                g2d.setColor(curTile.getFrontsideColour());
                g2d.fillRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public int getSquareSize() {
        return SQUARE_SIZE;
    }

    public void fillBacksideOfTile(int row, int column) {
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        Tile currentTile = gameBoard.getTile(row, column);

        g2d.setColor(currentTile.getBacksideColour());
        g2d.fillRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void fillTile(int row, int column) {
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        Tile currentTile = gameBoard.getTile(row, column);

        g2d.setColor(currentTile.getFrontsideColour());
        g2d.fillRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void fillCurTile(int row, int column) {
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE, SQUARE_SIZE);
    }
}

/**
 *  import java.util.AbstractMap;
    import java.util.EnumMap;
 *
 *     private AbstractMap<TileTypes, Color> colourMap;
 *
 * 	this.colourMap = new EnumMap<TileTypes, Color>(TileTypes.class);
 	fillMapColour();
 *
 *     public void fillMapColour() {
 	// A, B, C, D, E, F, G, H, BACKSIDE, OUTSIDE

 	colourMap.put(TileTypes.A, Color.GREEN);
 	colourMap.put(TileTypes.B, Color.BLUE);
 	colourMap.put(TileTypes.C, Color.RED);
 	colourMap.put(TileTypes.D, Color.ORANGE);
 	colourMap.put(TileTypes.E, Color.YELLOW);
 	colourMap.put(TileTypes.F, Color.CYAN);
 	colourMap.put(TileTypes.G, Color.PINK);
 	colourMap.put(TileTypes.H, Color.MAGENTA);
 	colourMap.put(TileTypes.BACKSIDE, Color.LIGHT_GRAY);
 	colourMap.put(TileTypes.OUTSIDE, Color.BLACK);
     }
 */

// Om vi vill skapa typ border runt brickorna.
// g2d.drawRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE, SQUARE_SIZE);




