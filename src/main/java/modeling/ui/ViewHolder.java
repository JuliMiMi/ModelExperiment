package modeling.ui;

import modeling.ui.view.View;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * Created by Oleksii Martyniuk on 08.05.2016.
 */
public class ViewHolder extends JFrame {

    public ViewHolder() {
        super();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Analysis of the company's competitiveness");
        setVisible(true);
        initCloseListener();
    }

    public void setView(View view) {
        JScrollPane scrollContainer = new JScrollPane(view.getContent());
        scrollContainer.getVerticalScrollBar().setUnitIncrement(20);
        setContentPane(scrollContainer);
        setJMenuBar(view.getMenu());
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
