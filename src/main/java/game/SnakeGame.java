package game;

import java.util.Arrays;

import java.util.Objects;
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

        }
    }

    public void gameLoop(Snake snake, Apple apple){

        apple.setBoard(board);
        apple.spawn();

        // Change direction using text
        Scanner scanner = new Scanner(System.in);
        String direction = scanner.nextLine();
        if (Objects.equals(direction, "w")) {
            snake.changeDirection(Direction.NORTH);
        } else if (Objects.equals(direction, "a")) {
            snake.changeDirection(Direction.WEST);
        } else if (Objects.equals(direction, "s")) {
            snake.changeDirection(Direction.SOUTH);
        } else if (Objects.equals(direction, "d")) {
            snake.changeDirection(Direction.EAST);
        }
        // ================

        snake.move();

        // if snake eats apple
        if (Arrays.toString(snake.getHead()).equals(Arrays.toString(apple.getApplePosition()))){
            snake.eat();
            apple.despawn();
            apple.spawn();
        }

        board.updateBoard(snake, apple);

        // print the board. update board upon pressing enter
        for (int[] element : board.getGrid()) {
            System.out.println(Arrays.toString(element));
        }
        System.out.println("===============================================");
        // ===============

        // Thread.sleep(1000); //pause for 1s

    }

}
