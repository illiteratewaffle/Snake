package game;

import java.util.Random;

public class Apple {

    private final int EMPTY = 0;
    private final int SNAKE = 1;
    private final int APPLE = 2;
    private final Random random = new Random();

    private int applePositionX;
    private int applePositionY;

    public Apple(){

    }

    public void spawn(Board board, Snake snake) {
        // check if there's already an apple on grid. if there's an apple, remove it
        if (board.getApplePosition() != null) {
            int[] applePosition = board.getApplePosition();
            int applePositionX = applePosition[0];
            int applePositionY = applePosition[1];
            board.setCell(applePositionX, applePositionY, EMPTY);
        }

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

    public int getApplePositionX() {
        return applePositionX;
    }

    public int getApplePositionY() {
        return applePositionY;
    }

}
