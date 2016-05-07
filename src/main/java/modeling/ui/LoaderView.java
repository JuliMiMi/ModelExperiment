package modeling.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julia on 07.05.2016.
 */
public class LoaderView {

    JLabel jlab;
    JButton jbtnStarted;

    void build() {

        JFrame jfrm = new JFrame("Ocenka Konkurentosposobnosti");
        jfrm.setLayout(new BorderLayout());
        jfrm.setSize(700, 400);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlab = new JLabel("Приложения для оценки конкурентоспособности потенциала предприятия",SwingConstants.CENTER);
        jlab.setVerticalTextPosition(SwingConstants.TOP);
        jlab.setHorizontalTextPosition(SwingConstants.CENTER);

        jbtnStarted = new JButton("Начать работу");

        JPanel jpnl = new JPanel();
        jpnl.setPreferredSize(new Dimension(600,300));
        jpnl.setOpaque(true);

        jpnl.add(new JLabel(new ImageIcon("started.jpg")));
        jfrm.add(jlab, BorderLayout.NORTH);
        jfrm.add(jbtnStarted, BorderLayout.SOUTH);

        jfrm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoaderView.build());
    }
}
