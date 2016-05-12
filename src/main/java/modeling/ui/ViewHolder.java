package modeling.ui;

import javax.swing.*;
import java.awt.event.*;

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
        menu();
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


    public void menu() {
        JMenuBar menuBar;
        JMenu method, operation;
        JMenuItem indicator, expert, financialPosition;
        JMenuItem test1, test2, test3;

        menuBar = new JMenuBar();

        method = new JMenu("Method");
        indicator = new JMenuItem("Indicator");
        expert = new JMenuItem("Expert");
        financialPosition = new JMenuItem("Financial position");
        method.add(indicator);
        method.add(expert);
        method.add(financialPosition);

        operation = new JMenu("Operation");
        test1 = new JMenuItem("Get Results");
        test2 = new JMenuItem("Test2");
        test3 = new JMenuItem("Test3");
        operation.add(test1);
        operation.add(test2);
        operation.add(test3);

        menuBar.add(method);
        menuBar.add(operation);

        setJMenuBar(menuBar);
    }

}
