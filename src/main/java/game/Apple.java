package game;

import java.util.Random;

/**
 * Represents an apple in the Snake game board.
 * <p>
 * Responsible for spawning the apple on a random empty cell,
 * tracking its position, and removing it when eaten.
 * </p>
 * javadoc generated by chatgpt. April 25th 2025
 */
public class Apple {

    // CELL STATES
    private final int EMPTY = 0;
    private final int SNAKE = 1;
    private final int APPLE = 2;
    private final Random random = new Random();


    private Board board;
    private int applePositionX;
    private int applePositionY;
    private int[] applePosition;

    /**
     * A constructor. Requires calling {@link #setBoard(Board)} before use.
     */
    public Apple(){

    }

    /**
     * Spawns the apple on a random empty cell of the board.
     * <p>
     * If an apple is already present, this method does nothing.
     * Otherwise, it repeatedly selects random coordinates until it finds an EMPTY cell,
     * places the apple there (updating the board and internal coordinates),
     * and caches the position.
     * </p>
     *
     * @throws IllegalStateException if {@link #setBoard(Board)} has not been called
     * javadoc generated by chatgpt. April 25th 2025
     */
    public void spawn() {
        // check if there's already an apple on grid. if there's an apple, remove it
        if (applePosition == null) { // if there is NO apple on board:
            int rows = board.getRows();
            int columns = board.getColumns();

            boolean applePlaced = false;
            while(!applePlaced){
                int randomX = random.nextInt(columns);
                int randomY = random.nextInt(rows);

                if(board.getCell(randomX, randomY) == EMPTY) {
                    board.setCell(randomX, randomY, APPLE);
                    applePlaced = true;

                    //update apple position
                    this.applePositionX = randomX;
                    this.applePositionY = randomY;
                }
            }
        }
    }

    /**
     * Resets apple tracking.
     * <p?
     * Resets applePosition field so that {@link #spawn()} can place a new apple.
     * </p>
     */
    public void despawn(){
        applePosition = null;

    }

    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * Get X coordinate of apple
     *
     * @return column index of apple
     */
    public int getApplePositionX() {
        return applePositionX;
    }

    /**
     * Get Y coordinate of apple
     *
     * @return row index of apple
     */
    public int getApplePositionY() {
        return applePositionY;
    }

    /**
     * Returns an array containing the apple's (x, y) position.
     *
     * @return an array of length 2 in cartesian coordinates
     */
    public int[] getApplePosition(){
        applePosition = new int[]{applePositionX,applePositionY};
        return applePosition;
    }
}
