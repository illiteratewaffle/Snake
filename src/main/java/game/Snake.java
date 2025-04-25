package game;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.LinkedList;

public class Snake {

    private LinkedList<int[]> body;
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
            body.removeLast();
        } else { // if (shouldGrow)
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
                (currentDirection == Direction.WEST  && newDirection == Direction.EAST)  ||
                (currentDirection == Direction.EAST  && newDirection == Direction.WEST)) {

            //error message: snake cant reverse
            System.err.println("snake cant reverse");

        } else {
            this.currentDirection = newDirection;
        }
    }

    /**
     * gets snake's head position
     * @return
     */
    public int[] getHead(){
        // i wish this had the other interpretation but this is fine too
        return body.get(0);
    }

}
