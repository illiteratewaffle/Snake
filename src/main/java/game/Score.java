package game;

/**
 * Utility class for managing the current game score.
 *
 * <p>Provides methods to update and retrieve the player's score based on the snake's size.</p>
 */
public class Score {

    private static int currentScore = 0;

    public static void updateScore(SnakeGame snakeGame) {
        currentScore = snakeGame.getSnake().getSize();
    }

    public static int getCurrentScore() {
        return currentScore;
    }
}
