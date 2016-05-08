package modeling.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Julia on 07.05.2016.
 */
public class LoaderView {

    JLabel nameApp;
    JButton pressStarted;

    public void build() {

        JFrame frame = new JFrame("Analysis of the company's competitiveness");
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Closing", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        nameApp = new JLabel("Application for assessment of the competitiveness potential of the enterprise", SwingConstants.CENTER);
        nameApp.setVerticalTextPosition(SwingConstants.TOP);
        nameApp.setHorizontalTextPosition(SwingConstants.CENTER);

        pressStarted = new JButton("Start work");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setOpaque(true);

        panel.add(new JLabel(new ImageIcon(ClassLoader.class.getResource("/started.jpg"))));
        frame.add(nameApp, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(pressStarted, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoaderView loaderView = new LoaderView();
            loaderView.build();
        });
    }
}
