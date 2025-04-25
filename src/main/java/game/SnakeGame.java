package game;

import java.util.Arrays;

public class SnakeGame {

    private Snake snake;
    private Apple apple;
    private Board board;
    private int boardRows;
    private int boardColumns;

    // to test program
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();

    }

    public SnakeGame(){
        startGame();

        // print the board
        for (int[] element : board.getGrid()) {
            System.out.println(Arrays.toString(element));
        }

    }

    public void startGame(){

        boardColumns = 20;
        boardRows = 20;
        board = new Board(boardColumns, boardRows);

        snake = new Snake(boardColumns/2, boardRows/2);

        apple = new Apple(board);

        board.updateBoard(snake, apple);

    }

}
