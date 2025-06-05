package controller;

import model.Direction;
import model.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputHandler {
    private final GameController controller;
    private Direction direction;

    public InputHandler(GameController controller) {
        this.controller = controller;
    }

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> controller.handleDirectionChange(Direction.LEFT);
                    case KeyEvent.VK_RIGHT -> controller.handleDirectionChange(Direction.RIGHT);
                    case KeyEvent.VK_UP -> controller.handleDirectionChange(Direction.UP);
                    case KeyEvent.VK_DOWN -> controller.handleDirectionChange(Direction.DOWN);
                    case KeyEvent.VK_SPACE -> controller.handlePause();
                    case KeyEvent.VK_ENTER -> {
                        if (controller.getGameState() == GameState.GAME_OVER ||
                                controller.getGameState() == GameState.START_SCREEN) {
                            controller.restartGame();
                        }
                    }
                }
            }
        };
    }

    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (controller.getGameState() == GameState.START_SCREEN) {
                    controller.handleStartClick(e.getX(), e.getY());
                }
            }
        };
    }
}
