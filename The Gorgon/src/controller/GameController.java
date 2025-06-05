package controller;

import model.Apple;
import model.Direction;
import model.GameState;
import model.Snake;
import view.GameOverScreen;
import view.GamePanel;
import view.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 20;
    private static final int DELAY = 75;

    private final Timer timer;
    private GameState gameState;
    private Snake snake;
    private Apple apple;
    private int score;

    private final JPanel container;
    private final GamePanel gamePanel;
    private final StartScreen startScreen;
    private final InputHandler inputHandler;

    public GameController() {
        container = new JPanel(new CardLayout());
        gamePanel = new GamePanel(UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
        startScreen = new StartScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
        inputHandler = new InputHandler(this);

        container.add(startScreen, "START");
        container.add(gamePanel, "GAME");

        startScreen.addMouseListener(inputHandler.getMouseAdapter());
        startScreen.addKeyListener(inputHandler.getKeyAdapter());
        gamePanel.addKeyListener(inputHandler.getKeyAdapter());

        timer = new Timer(DELAY, this);
        initializeGame();
    }

    private void initializeGame() {
        gameState = GameState.START_SCREEN;
        snake = new Snake((SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE), UNIT_SIZE);
        apple = new Apple(UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
        apple.spawn(snake.getSegments());
        score = 0;

        startScreen.setFocusable(true);
        startScreen.requestFocusInWindow();
    }

    public JPanel getView() {
        return container;
    }

    public void startGame() {
        gameState = GameState.PLAYING;
        ((CardLayout) container.getLayout()).show(container, "GAME");
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        timer.start();
    }

    public void gameOver() {
        GameOverScreen gameOverScreen = new GameOverScreen(SCREEN_WIDTH, SCREEN_HEIGHT, score);
        container.add(gameOverScreen, "GAME_OVER");
        ((CardLayout) container.getLayout()).show(container, "GAME_OVER");
        gameOverScreen.addKeyListener(inputHandler.getKeyAdapter());
        gameOverScreen.setFocusable(true);
        gameOverScreen.requestFocusInWindow();

        gameState = GameState.GAME_OVER;
        timer.stop();
    }

    public void restartGame() {
        initializeGame();
        startGame();
    }

    public void update() {
        if (gameState == GameState.PLAYING) {
            snake.move();

            // Check collisions
            Point head = snake.getHeadPosition();
            if (head.x < 0 || head.y < 0 ||
                    head.x >= SCREEN_WIDTH || head.y >= SCREEN_HEIGHT ||
                    snake.getSegments().size() < snake.getBodyLength()) {
                gameOver();
                return;
            }

            // Check apple collision
            if (head.equals(new Point(apple.getX(), apple.getY()))) {
                score++;
                snake.setBodyLength(snake.getBodyLength() + 1);
                apple.spawn(snake.getSegments());
            }

            gamePanel.updateGame(snake, apple, score);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    // Methods for input handling
    public void handleDirectionChange(Direction direction) {
        if (gameState == GameState.PLAYING) {
            snake.setDirection(direction);
        }
    }

    public void handleStartClick(int x, int y) {
        if (gameState == GameState.START_SCREEN && startScreen.isStartButtonClicked(x, y)) {
            startGame();
        }
    }

    public void handlePause() {
        if (gameState == GameState.PLAYING) {
            gameState = GameState.PAUSED;
            timer.stop();
        } else if (gameState == GameState.PAUSED) {
            gameState = GameState.PLAYING;
            timer.start();
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
