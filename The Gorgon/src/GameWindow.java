import controller.GameController;

import javax.swing.*;

public class GameWindow {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 600;

    public void startGame() {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setResizable(false);

        GameController controller = new GameController();
        frame.add(controller.getView());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
