package modeling.ui.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julia on 07.05.2016.
 */
public class GreetingsView extends JPanel implements View {

    private Dimension VIEW_SIZE = new Dimension(400, 200);

    public GreetingsView() {
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
        startButton.addActionListener(event -> ViewManager.getViewHolder().setView(ViewManager.getIndicatorMethodView()));
        return startButton;
    }

    @Override
    public JPanel getContent() {
        return this;
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu method = new JMenu("Method");
        menuBar.add(method);

        JMenuItem indicator = new JMenuItem("Indicator");
        indicator.addActionListener(event -> ViewManager.getViewHolder().setView(ViewManager.getIndicatorMethodView()));
        method.add(indicator);

        JMenuItem expert = new JMenuItem("Expert");
        expert.addActionListener(event -> {
            //TODO switch into ExpertMethodView
        });
        method.add(expert);

        JMenuItem financialPosition = new JMenuItem("Financial position");
        financialPosition.addActionListener(event -> {
            //TODO switch into FinancialPositionMethodView
        });
        method.add(financialPosition);

        return menuBar;
    }
}
