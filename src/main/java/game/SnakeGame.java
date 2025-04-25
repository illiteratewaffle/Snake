package game;

import java.util.Arrays;

public class SnakeGame {

    private Snake snake;
    private Apple apple;
    private Board board;
    private int boardRows;
    private int boardColumns;

    // to test program
    public static void main(String[] args) throws InterruptedException {
        SnakeGame game = new SnakeGame();

    }

    public SnakeGame() throws InterruptedException {
        startGame();



    }

    public void startGame() throws InterruptedException {

        boardColumns = 20;
        boardRows = 20;
        board = new Board(boardColumns, boardRows);

        while(true) {
            gameLoop();

            // print the board
            for (int[] element : board.getGrid()) {
                System.out.println(Arrays.toString(element));
            }
            System.out.println("===============================================");

            Thread.sleep(5000);

        }
    }

    public void gameLoop(){
        snake = new Snake(boardColumns/2, boardRows/2);

        apple = new Apple(board);

        board.updateBoard(snake, apple);
    }

}
