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

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {

                Tile curTile = gameBoard.getTile(row, column);
                TileState state = curTile.getState();

                if (state == TileState.IS_UP) {
                    g2d.setColor(curTile.getFrontsideColour());
                }
                else if (state == TileState.IS_DOWN || state == TileState.IS_SAME_TILE) {
                    g2d.setColor(curTile.getBacksideColour());
                }

                g2d.fillRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE,
                             SQUARE_SIZE);
            }
        }
    }

    public void fillTile(TileState state, int row, int column) {
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        Tile currentTile = gameBoard.getTile(row, column);

        if (state == TileState.IS_UP) {
            g2d.setColor(currentTile.getFrontsideColour());
        }
        else if (state == TileState.IS_DOWN ||
                 state == TileState.IS_SAME_TILE) {
            g2d.setColor(currentTile.getBacksideColour());
        }

        g2d.fillRect(column * SQUARE_SIZE + column * SPACE, row * SQUARE_SIZE + row * SPACE, SQUARE_SIZE,
                     SQUARE_SIZE);
    }
}


