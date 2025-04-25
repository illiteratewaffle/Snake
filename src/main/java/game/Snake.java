package game;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.Arrays;
import java.util.LinkedList;

public class Snake {

    private LinkedList<int[]> body;
    private int[] lastRemovedTail;
    private int size;
    private Direction currentDirection;
    private boolean shouldGrow;

    /**
     *  snake starts at center of board
     *  snake travels east
     */
    public Snake(int startX, int startY){
        body = new LinkedList<>();
        body.add(new int[]{startX, startY});

        currentDirection = Direction.EAST;
        shouldGrow = false;

        this.size = 1;
    }

    public void move(){

        int[] head = getHead();
        int x = head[0];
        int y = head[1];

        if (currentDirection == Direction.NORTH){
            y--;
        } else if (currentDirection == Direction.WEST) {
            x--;
        } else if (currentDirection == Direction.SOUTH) {
            y++;
        } else { //if (currentDirection == Direction.EAST)
            x++;
        }

        body.addFirst(new int[]{x,y});

        if (!shouldGrow) {
            lastRemovedTail = body.removeLast();

        } else { // if (shouldGrow)
            lastRemovedTail = null;
            shouldGrow = false;
            size++;
        }
    }

    public void eat(){
        shouldGrow = true;
    }

    public void changeDirection(Direction newDirection){
        if ((currentDirection == Direction.NORTH && newDirection == Direction.SOUTH) ||
                (currentDirection == Direction.SOUTH && newDirection == Direction.NORTH) ||
                (currentDirection == Direction.WEST  && newDirection == Direction.EAST) ||
                (currentDirection == Direction.EAST  && newDirection == Direction.WEST)) {

            //error message: snake cant reverse direction
            System.err.println("snake cant reverse direction");

        } else {
            this.currentDirection = newDirection;
        }
    }

    public boolean hasCrashed(int boardColumns, int boardRows){

        int[] head = getHead();
        int x = head[0];
        int y = head[1];

        // crashes into wall
        if (x < 0 || y < 0 || x >= boardColumns || y >= boardRows){
            return true;
        }

        // crashes into itself
        for (int i = 1; i < body.size(); i++){
            if (Arrays.equals(head, body.get(i))){
                return true;
            }
        }

        // doesnt crash
        return false;
    }

    public LinkedList<int[]> getBody(){
        return body;
    }

    /**
     * gets snake's head position
     * @return
     */
    public int[] getHead(){
        // i wish this had the other interpretation but this is fine too
        return body.get(0);
    }

    public boolean getShouldGrow(){
        return shouldGrow;
    }

    public int[] getLastRemovedTail() {
        return lastRemovedTail;
    }

}
