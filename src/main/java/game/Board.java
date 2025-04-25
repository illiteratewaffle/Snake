package game;

import java.util.LinkedList;

public class Board {

    private final int EMPTY = 0;
    private final int SNAKE = 1;
    private final int APPLE = 2;

    private final int rows;
    private final int columns;
    private int[][] grid;

    private LinkedList<int[]> snakeBody;
    private int[] appleBody;

    public Board(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
        this.grid = new int[rows][columns]; // rows = y, columns = x
        clearBoard();
    }

    public void updateBoard(Snake snake, Apple apple){
        snakeBody = snake.getBody();
        appleBody = apple.getApplePosition();

        // updates board with snake body position
        for(int i = 0; i < snakeBody.size(); i++){
            setCell(snakeBody.get(i)[0] , snakeBody.get(i)[1], SNAKE);
        }

        // updates board with apple position
        setCell(appleBody[0], appleBody[1], APPLE);
    }

    /**
     * Reset board
     */
    public void clearBoard(){
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++) {
                grid[y][x] = EMPTY;
            }
        }
    }

    public int getCell(int x, int y){
        return grid[y][x]; // y is row, x is column
    }

    public void setCell(int x, int y, int state){
        grid[y][x] = state;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getGrid(){
        return grid;
    }

    public int[] getApplePosition(){
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++) {
                if (grid[y][x] == APPLE) {
                    return new int[]{x,y};
                }
            }
        }

        return null;
    }

}
