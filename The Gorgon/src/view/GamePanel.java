package view;

import model.Apple;
import model.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GamePanel extends JPanel {
    private final int unitSize;
    private final int screenWidth;
    private final int screenHeight;
    private Snake snake;
    private Apple apple;
    private int score;

    public GamePanel(int unitSize, int screenWidth, int screenHeight) {
        this.unitSize = unitSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public void updateGame(Snake snake, Apple apple, int score) {
        this.snake = snake;
        this.apple = apple;
        this.score = score;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snake != null && apple != null) {
            drawSnake(g);
            drawApple(g);
            drawScore(g);
        }
    }

    private void drawSnake(Graphics g) {
        int[] x = snake.getXCoordinates();
        int[] y = snake.getYCoordinates();

        for (int i = 0; i < snake.getBodyLength(); i++) {
            g.setColor(i == 0 ? Color.GREEN : new Color(51, 163, 10));
            g.fillRect(x[i], y[i], unitSize, unitSize);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(apple.getX(), apple.getY(), unitSize, unitSize);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        g.drawString("Score: " + score, 10, 40);
    }
}