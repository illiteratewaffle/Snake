package game;

import java.util.Arrays;

import java.util.Scanner;

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

        snake = new Snake(boardColumns/2, boardRows/2); //spawn snake in middle of board

        apple = new Apple();

        while(true) {
            gameLoop(snake, apple);

            // print the board. update board upon pressing enter
            for (int[] element : board.getGrid()) {
                System.out.println(Arrays.toString(element));
            }
            System.out.println("===============================================");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        }
    }

    public void gameLoop(Snake snake, Apple apple){

        apple.setBoard(board);
        apple.spawn();
        snake.move();
        board.updateBoard(snake, apple);

    }

}
