package model;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class Apple {
    private int x;
    private int y;
    private final int unitSize;
    private final int screenWidth;
    private final int screenHeight;
    private final Random random;

    public Apple(int unitSize, int screenWidth, int screenHeight) {
        this.unitSize = unitSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        random = new Random();
    }

    public void spawn(Set<Point> snakeSegments) {
        do {
            x = random.nextInt(screenWidth / unitSize) * unitSize;
            y = random.nextInt(screenHeight / unitSize) * unitSize;
        } while (snakeSegments.contains(new Point(x, y)));
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}
