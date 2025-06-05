package model;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Snake {
    private final int[] xCoordinates;
    private final int[] yCoordinates;
    private final Set<Point> segments;
    private int bodyLength;
    private Direction direction;
    private final int unitSize;

    public Snake(int maxUnits, int unitSize) {
        this.unitSize = unitSize;
        xCoordinates = new int[maxUnits];
        yCoordinates = new int[maxUnits];
        segments = new HashSet<>();
        bodyLength = 6;
        direction = Direction.RIGHT;
        initializeSnake();
    }

    private void initializeSnake() {
        for (int i = 0; i < bodyLength; i++) {
            xCoordinates[i] = unitSize * (bodyLength - i - 1);
            yCoordinates[i] = 0;
            segments.add(new Point(xCoordinates[i], yCoordinates[i]));
        }
    }

    public void move() {
        // Remove tail from segments
        segments.remove(new Point(xCoordinates[bodyLength - 1], yCoordinates[bodyLength - 1]));

        // Move body
        for (int i = bodyLength; i > 0; i--) {
            xCoordinates[i] = xCoordinates[i - 1];
            yCoordinates[i] = yCoordinates[i - 1];
        }

        // Move head
        switch (direction) {
            case RIGHT -> xCoordinates[0] += unitSize;
            case LEFT -> xCoordinates[0] -= unitSize;
            case UP -> yCoordinates[0] -= unitSize;
            case DOWN -> yCoordinates[0] += unitSize;
        }

        // Add new head position
        segments.add(new Point(xCoordinates[0], yCoordinates[0]));
    }

    public void setDirection(Direction newDirection) {
        // Prevent 180-degree turns
        if ((direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection != Direction.LEFT) ||
                (direction == Direction.UP && newDirection != Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection != Direction.UP)) {
            direction = newDirection;
        }
    }

    // Getters and setters
    public int[] getXCoordinates() { return xCoordinates; }
    public int[] getYCoordinates() { return yCoordinates; }
    public int getBodyLength() { return bodyLength; }
    public void setBodyLength(int bodyLength) { this.bodyLength = bodyLength; }
//    public Direction getDirection() { return direction; }
    public Set<Point> getSegments() { return segments; }
    public Point getHeadPosition() { return new Point(xCoordinates[0], yCoordinates[0]); }
}
