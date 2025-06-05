package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class StartScreen extends JPanel {
    private final int buttonX = 500;
    private final int buttonY = 200;

    public StartScreen(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw button
        int buttonWidth = 200;
        int buttonHeight = 80;
        int arc = 40;

        GradientPaint gradient = new GradientPaint(
                buttonX, buttonY, new Color(100, 200, 100),
                buttonX, buttonY + buttonHeight, new Color(0, 150, 0)
        );
        g2d.setPaint(gradient);
        g2d.fill(new RoundRectangle2D.Double(buttonX, buttonY, buttonWidth, buttonHeight, arc, arc));

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new RoundRectangle2D.Double(buttonX, buttonY, buttonWidth, buttonHeight, arc, arc));

        // Draw play icon
        int triangleSize = 30;
        int[] xPoints = {buttonX + 50, buttonX + 50, buttonX + 50 + triangleSize};
        int[] yPoints = {buttonY + 25, buttonY + 55, buttonY + 40};
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Start", buttonX + 100, buttonY + 50);
    }

    public boolean isStartButtonClicked(int x, int y) {
        return x >= buttonX && x <= buttonX + 200 && y >= buttonY && y <= buttonY + 80;
    }
 }
