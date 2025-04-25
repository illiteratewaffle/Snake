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

    public void updateBoard(Snake snake, Apple apple) {

        // wipe only the cell that became empty
        int[] oldTail = snake.getLastRemovedTail();
        if (oldTail != null) {
            setCell(oldTail[0], oldTail[1], EMPTY);
        }

        //  draw snake
        for (int[] part : snake.getBody()) {
            setCell(part[0], part[1], SNAKE);
        }

        // draw apple
        int[] applePos = apple.getApplePosition();
        if (applePos != null) {
            setCell(applePos[0], applePos[1], APPLE);
        }
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
