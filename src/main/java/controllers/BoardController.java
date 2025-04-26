package controllers;

import game.Direction;
import game.SnakeGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable, EventHandler<KeyEvent> {

    private static final int CELL_SIZE = 30;
    private static final Color EMPTY  = Color.web("2b2d30");
    private static final Color SNAKE  = Color.LIMEGREEN;
    private static final Color APPLE  = Color.RED;

    @FXML
    private GridPane boardGrid;

    private SnakeGame snakeGame;
    private Rectangle[][] cells;
    private int rows;
    private int cols;

    /* chatgpt: timeline that drives the game */
    private Timeline clock;

    // chatgpt: grid initialization and animations
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        snakeGame = new SnakeGame();
        rows = snakeGame.getBoardRows();
        cols = snakeGame.getBoardColumns();
        cells = new Rectangle[rows][cols];

        /* lock GridPane size to exact board dimensions */
        double boardSide = cols * CELL_SIZE;           // 20 × 30 = 600
        boardGrid.setMinSize(boardSide, boardSide);
        boardGrid.setMaxSize(boardSide, boardSide);
        HBox.setHgrow(boardGrid, Priority.NEVER);
        boardGrid.getColumnConstraints().clear();
        boardGrid.getRowConstraints().clear();


        /* fixed-pixel column & row constraints */
        for (int c = 0; c < cols; c++) {
            ColumnConstraints cc = new ColumnConstraints(CELL_SIZE);
            cc.setMinWidth(CELL_SIZE);
            cc.setMaxWidth(CELL_SIZE);
            boardGrid.getColumnConstraints().add(cc);
        }
        for (int r = 0; r < rows; r++) {
            RowConstraints rc = new RowConstraints(CELL_SIZE);
            rc.setMinHeight(CELL_SIZE);
            rc.setMaxHeight(CELL_SIZE);
            boardGrid.getRowConstraints().add(rc);
        }

        /* create rectangles for every cell */
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
                rect.setFill(EMPTY);
                boardGrid.add(rect, x, y);
                cells[y][x] = rect;
            }
        }

        boardGrid.setGridLinesVisible(true);
        boardGrid.setFocusTraversable(true);
        boardGrid.setOnKeyPressed(this);
        boardGrid.requestFocus();

        snakeGame.getApple().spawn();
        snakeGame.updateBoard();
        paint();

        clock = new Timeline(new KeyFrame(Duration.millis(250), new StepHandler())); // (1s = 1e3 ms)
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    /* chatgpt: called by StepHandler once per second */
    public void nextStep() {
        moveAndRepaint();
    }

    // keyboard inputs
    @Override
    public void handle(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == KeyCode.W || key == KeyCode.UP) {
            snakeGame.getSnake().changeDirection(Direction.NORTH);
        } else if (key == KeyCode.A || key == KeyCode.LEFT) {
            snakeGame.getSnake().changeDirection(Direction.WEST);
        } else if (key == KeyCode.S || key == KeyCode.DOWN) {
            snakeGame.getSnake().changeDirection(Direction.SOUTH);
        } else if (key == KeyCode.D || key == KeyCode.RIGHT) {
            snakeGame.getSnake().changeDirection(Direction.EAST);
        }

        // todo: add pause game feature

    }

    /* chatgpt: advance the model and repaint the board */
    private void moveAndRepaint() {
        snakeGame.moveSnake();
        if (snakeGame.checkCollision()) return;
        snakeGame.checkAppleEat();
        snakeGame.updateBoard();
        paint();
    }

    // colour "paint" the board
    private void paint() {
        int[][] grid = snakeGame.getGrid();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                switch (grid[y][x]) {
                    case 1 -> cells[y][x].setFill(SNAKE);
                    case 2 -> cells[y][x].setFill(APPLE);
                    default -> cells[y][x].setFill(EMPTY);
                }
            }
        }
    }

    private class StepHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            nextStep();
        }
    }

}
