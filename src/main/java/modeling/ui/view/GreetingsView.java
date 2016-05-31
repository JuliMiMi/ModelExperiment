package modeling.ui.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Created by Julia on 07.05.2016.
 */
public class GreetingsView extends JPanel implements View {

    private Dimension VIEW_SIZE = new Dimension(600, 300);

    public GreetingsView() {
        super();
        build();
    }

    private void build() {
        this.setLayout(new BorderLayout());
        setSize(VIEW_SIZE);
        setOpaque(true);


        JPanel jpn1 = new JPanel();
        JPanel jpn3 = new JPanel();

        add(jpn1, BorderLayout.NORTH);
        add(jpn3, BorderLayout.SOUTH);
        jpn1.add(buildAppName());
        jpn1.setBackground(Color.lightGray);
        jpn3.setBackground(Color.WHITE);
        jpn1.setPreferredSize(new Dimension(400, 35));
        add(buildBackground(), BorderLayout.CENTER);
        jpn3.setPreferredSize(new Dimension(400, 50));
        jpn3.add(buildStartButton());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

    }

    private JLabel buildAppName() {

        JLabel appName = new JLabel("COMPETITIVENESS ASSEMENT", SwingConstants.CENTER);
        appName.setFont(new Font("Cambria", Font.PLAIN, 22));
        appName.setForeground(Color.BLACK);
        appName.setVerticalTextPosition(SwingConstants.TOP);
        appName.setHorizontalTextPosition(SwingConstants.CENTER);
        return appName;
    }

    private JLabel buildBackground() {
        ImageIcon background = new ImageIcon(ClassLoader.class.getResource("/bisiness.jpg"));
        JLabel backgroundHolder = new JLabel();
        backgroundHolder.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        backgroundHolder.setMaximumSize(VIEW_SIZE);
        backgroundHolder.setIcon(background);
        return backgroundHolder;
    }

    private JButton buildStartButton() {
        JButton startButton = new JButton("ПОЧАТИ РОБОТУ");
        startButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        startButton.setPreferredSize(new Dimension(500, 50));
        startButton.addActionListener(event -> ViewManager.getViewHolder().setView(ViewManager.getIndicatorMethodView()));
        return startButton;
    }

    @Override
    public JPanel getContent() {
        return this;
    }

  /*  @Override
    public JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();

       *//* JMenu method = new JMenu("Method");
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

        return menuBar;*//*
    }*/
}
