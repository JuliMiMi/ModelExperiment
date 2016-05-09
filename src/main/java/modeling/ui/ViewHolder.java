package modeling.ui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * Created by Oleksii Martyniuk on 08.05.2016.
 */
public class ViewHolder extends JFrame {

    ViewHolder() {
        super();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Analysis of the company's competitiveness");
        setResizable(false);
        setVisible(true);
        initCloseListener();
    }

    public void setView(JPanel view) {
        JScrollPane scrollContainer = new JScrollPane(view);
        setContentPane(scrollContainer);
        pack();
    }

    private void initCloseListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (OK_OPTION == showConfirmDialog(e.getWindow(), "Are you sure?", "Closing", OK_CANCEL_OPTION)) {
                    System.exit(0);
                }
            }
        });
    }

}
