package game;

public class Score {

    private static int currentScore = 0;

    public static void updateScore(SnakeGame snakeGame) {
        currentScore = snakeGame.getSnake().getSize();
    }

    public static int getCurrentScore() {
        return currentScore;
    }
}
