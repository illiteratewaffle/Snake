package game;

import java.util.Random;

public class Apple {

    private final int EMPTY = 0;
    private final int SNAKE = 1;
    private final int APPLE = 2;
    private final Random random = new Random();


    private Board board;
    private int applePositionX;
    private int applePositionY;
    private int[] applePosition;

    public Apple(){

    }

    /**
     * spawns apple in a random empty spot
     *
     */
    public void spawn() {
        // check if there's already an apple on grid. if there's an apple, remove it
        if (board.getApplePosition() == null) { // if there is NO apple on board:
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

    public void setBoard(Board board){
        this.board = board;
    }

    public int getApplePositionX() {
        return applePositionX;
    }

    public int getApplePositionY() {
        return applePositionY;
    }

    public int[] getApplePosition(){
        applePosition = new int[]{applePositionX,applePositionY};
        return applePosition;
    }
}
