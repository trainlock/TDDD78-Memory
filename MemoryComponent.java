package se.liu.ida.linbe810.tddd78.memory;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.logging.Logger;

/**
 * se.liu.ida.linbe810.tddd78.memory.MemoryComponent is the class for all graphic contexts which
 * allow the components to be draw onto the screen.
 *
 * <p>
 * Operations which fill a figure of a se.liu.ida.linbe810.tddd78.memory.Tile according to the
 * Tiles size and predefined Image. The number of Tiles that
 * are drawn depends on the size of the gameboard.
 * Operations which draw a line between all Tiles to seperate them.
 * <p>
 * Some important points to consider are that the figure will
 * only be drawn according to its state.
 * <p>
 * All coordinates which appear as arguments to the methods of
 * this object are relative to the translation origin.
 */

public class MemoryComponent extends JComponent
{
    private Board gameBoard;
    public final static int SQUARE_SIZE = Tile.getTileSize();
    public final static int SPACE = 2;
    private AbstractMap<TileTypes,Image> imageMap;
    private Logger logger;

    public MemoryComponent(final Board gameBoard) {
	this.gameBoard = gameBoard;
        this.imageMap = new EnumMap<TileTypes, Image>(TileTypes.class);
        logger = Logger.getLogger(Board.class.getName());

        imageMap.put(TileTypes.TILE_TYPE_A, loadImageResource("Pictures/duck1.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_B, loadImageResource("Pictures/duck2.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_C, loadImageResource("Pictures/duck3.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_D, loadImageResource("Pictures/duck4.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_E, loadImageResource("Pictures/kungen.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_F, loadImageResource("Pictures/duck5.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_G, loadImageResource("Pictures/duck6.jpg").getImage());
        imageMap.put(TileTypes.TILE_TYPE_H, loadImageResource("Pictures/duck7.jpg").getImage());

        logger.info("Images successfully loaded!");
    }

    @Override
    public Dimension getPreferredSize() {
	int width = SQUARE_SIZE * gameBoard.getWidth() + SPACE;
	int height = SQUARE_SIZE * gameBoard.getHeight() + SPACE;
        return new Dimension(width, height);
    }
    /**
     * Tries to load the given images into the image map
     * @param path The adress of the image
     * @return if found, the URL for the image, if not - simply returns
     */
    private static ImageIcon loadImageResource(String path) {
                  try {
                      URL resourceUrl = MemoryComponent.class.getClassLoader().getResource(path);
                      System.out.println(resourceUrl);
                      if (resourceUrl != null) {
                          return new ImageIcon(resourceUrl);
                      } else {
                          System.out.println("Could not load resource " + path);
                      }

                  } catch (RuntimeException e) {
                      e.printStackTrace();
                  }

                  return null;
        }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
        fillBoardImages(gameBoard, g);
    }

    /**
     * Takes a number and returns its square root.
     *
     * @param gameBoard     the board of the game, with all the tiles
     * @param g             Graphics object
     */
    public void fillBoardImages(Board gameBoard, Graphics g) {
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
                    g2d.drawImage(imageMap.get(curTile.getTileType()), column*SQUARE_SIZE, row*SQUARE_SIZE,null);
                }
                else if (state == TileState.IS_DOWN || state == TileState.IS_SAME_TILE) {
                    g2d.setColor(curTile.getBacksideColour());
                    g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE,
                                SQUARE_SIZE);
                }

                drawSpace(row, column, g2d);
            }
        }
    }

    /**
     * Draws a space between the tiles
     *
     * @param row       The y-coordinate of the chosen tile
     * @param column    The x-coordinate of the chosen tile
     * @param g         Graphics object
     */
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

    /**
     * Fills the tiles with its corresponding image.
     *
     * @param state     The state of the current tile
     * @param row       The y-coordinate of the chosen tile
     * @param column    The x-coordinate of the chosen tile
     */
    public void fillTile(TileState state, int row, int column) {
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        Tile currentTile = gameBoard.getTile(row, column);

        if (state == TileState.IS_UP) {
            g2d.drawImage(imageMap.get(currentTile.getTileType()), column*SQUARE_SIZE, row*SQUARE_SIZE,null);
        }
        else if (state == TileState.IS_DOWN ||
                 state == TileState.IS_SAME_TILE) {
            g2d.setColor(currentTile.getBacksideColour());

            //SKRIV OM!!!!!!!!
            g2d.fillRect(column * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }

        drawSpace(row, column, g2d);
    }
}


