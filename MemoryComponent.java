package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;

public class MemoryComponent extends JComponent
{
    private Board gameBoard;
    public final static int SQUARE_SIZE = Tile.getTileSize();
    public final static int SPACE = 2;

    public MemoryComponent(final Board gameBoard) {
	this.gameBoard = gameBoard;
    }

    @Override
    public Dimension getPreferredSize() {
	int width = SQUARE_SIZE * gameBoard.getWidth() + SPACE;
	int height = SQUARE_SIZE * gameBoard.getHeight() + SPACE;
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

        //for (x = 0; x < 4; )

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

                g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE,
                           SQUARE_SIZE);

                drawSpace(row, column, g2d);
            }
        }
    }

    public void drawSpace(int row, int column, Graphics g) {
        int height = gameBoard.getHeight();
        // height == row == y
        int width = gameBoard.getWidth();
        // width == column == x

        g.setColor(Color.BLACK);

        g.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, (width + 1) * SQUARE_SIZE, SPACE);
        g.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SPACE, (height + 1) * SQUARE_SIZE);

        if (row == 0 && column == 0) {
            g.fillRect(height * SQUARE_SIZE, row * SQUARE_SIZE, SPACE, (height + 1) * SQUARE_SIZE);
            g.fillRect(column * SQUARE_SIZE, width * SQUARE_SIZE, (width + 1) * SQUARE_SIZE, SPACE);
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

        g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

        drawSpace(row, column, g2d);
    }
}


