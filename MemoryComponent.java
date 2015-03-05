package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class MemoryComponent extends JComponent
{
    private Board gameBoard;
    private EnumMap<Tiles, Color> colourMap;
    private final static int SQUARE_SIZE = 30;

    public MemoryComponent(final Board gameBoard) {
	this.gameBoard = gameBoard;
	this.colourMap = new EnumMap<Tiles, Color>(Tiles.class);
	fillMapColour();
    }

    public Dimension getPreferredSize() {
	int width = SQUARE_SIZE * gameBoard.getWidth();
	int height = SQUARE_SIZE * gameBoard.getHeight();
        System.out.println("Widht: " + gameBoard.getWidth() + " Height :" + gameBoard.getHeight());
        return new Dimension(width, height);
    }

    @Override public void paintComponent(Graphics g) {
	super.paintComponent(g);
	fillBoardColour(gameBoard, g);
    }

    public void fillMapColour() {
	// A, B, C, D, E, F, G, H, BACKSIDE, OUTSIDE

	colourMap.put(Tiles.A, Color.GREEN);
	colourMap.put(Tiles.B, Color.BLUE);
	colourMap.put(Tiles.C, Color.RED);
	colourMap.put(Tiles.D, Color.ORANGE);
	colourMap.put(Tiles.E, Color.YELLOW);
	colourMap.put(Tiles.F, Color.CYAN);
	colourMap.put(Tiles.G, Color.PINK);
	colourMap.put(Tiles.H, Color.MAGENTA);
	colourMap.put(Tiles.BACKSIDE, Color.WHITE);
	colourMap.put(Tiles.OUTSIDE, Color.GRAY);
    }

    public void fillBoardColour(Board gameBoard, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int height = gameBoard.getHeight();
        // height == row
       	int width = gameBoard.getWidth();
        // width == column


        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {

                Tiles currentTile = gameBoard.getTile(row, column);

                g2d.setColor(colourMap.get(currentTile));
                g2d.drawRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
