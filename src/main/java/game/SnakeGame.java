package game;

public class SnakeGame {

    private Snake snake;
    private Apple apple;
    private Board board;
    private int boardRows;
    private int boardColumns;


    public void main(String[] args) {
        startGame();
    }

    public void startGame(){

        boardColumns = 20;
        boardRows = 20;
        board = new Board(boardColumns, boardRows);

        snake = new Snake(boardColumns/2, boardRows/2);



    }

}
