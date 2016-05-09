package modeling.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julia on 07.05.2016.
 */
public class GreetingsView extends JPanel {

    private Dimension VIEW_SIZE = new Dimension(400, 200);

    GreetingsView() {
        super();
        build();
    }

    private void build() {
        this.setLayout(new BorderLayout());
    setSize(VIEW_SIZE);
    setOpaque(true);

    add(buildAppName(), BorderLayout.NORTH);
    add(buildBackground(), BorderLayout.CENTER);
    add(buildStartButton(), BorderLayout.SOUTH);
}

    private JLabel buildAppName() {
        JLabel appName = new JLabel("Application for assessment of the competitiveness potential of the enterprise", SwingConstants.CENTER);
        appName.setVerticalTextPosition(SwingConstants.TOP);
        appName.setHorizontalTextPosition(SwingConstants.CENTER);
        return appName;
    }

    private JLabel buildBackground() {
        ImageIcon background = new ImageIcon(ClassLoader.class.getResource("/started.jpg"));
        JLabel backgroundHolder = new JLabel();
        backgroundHolder.setMaximumSize(VIEW_SIZE);
        backgroundHolder.setIcon(background);
        return backgroundHolder;
    }

    private JButton buildStartButton() {
        JButton startButton = new JButton("Start work");
        startButton.addActionListener(event -> {
            //TODO SWITCH VIEW TO "MethodView"
        });
        return startButton;
    }

}
