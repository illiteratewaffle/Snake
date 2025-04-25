package game;

public class Board {

    private final int EMPTY = 0;
    private final int SNAKE = 1;
    private final int APPLE = 2;

    private final int rows;
    private final int columns;
    private int[][] grid;

    public Board(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
        this.grid = new int[rows][columns]; // rows = y, columns = x
        clearBoard();
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
