package view;

import javax.swing.*;
import java.awt.*;

import controller.GameController;

public class GameOverScreen extends JPanel {
    private final int score;

    public GameOverScreen(int width, int height, int score) {
        this.score = score;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));

        FontMetrics metrics = getFontMetrics(g.getFont());
        String gameOver = "Game Over";
        String scoreText = "Score: " + score;

        g.drawString(gameOver, (getWidth() - metrics.stringWidth(gameOver)) / 2, getHeight() / 2);
        g.drawString(scoreText, (getWidth() - metrics.stringWidth(scoreText)) / 2, getHeight() / 4);
    }
}
