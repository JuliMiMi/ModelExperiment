package modeling.ui;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julia on 09.05.2016.
 */
public class IndicatorMethodView extends JPanel {


    IndicatorMethodView() {
        super();
        build();
    }

    private void build() {
        this.setLayout(new BorderLayout(10, 10));
        setOpaque(true);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildCompaniesHolder(), BorderLayout.SOUTH);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new FlowLayout());

        JCheckBox compareCompaniesCheckbox = new JCheckBox("Compare several companies");
        //todo add listener for this checkbox
        header.add(compareCompaniesCheckbox);

        header.add(new JLabel("Number of competitors"));

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner companiesCounter = new JSpinner(spinnerModel);
        companiesCounter.setSize(new Dimension(40, 20));
        //todo add listener for this counter
        header.add(companiesCounter);

        return header;
    }

    private JPanel buildCompaniesHolder() {
        JPanel companiesHolder = new JPanel(new GridLayout(0, 2, 10, 10));

        companiesHolder.add(new CompanyView());
        companiesHolder.add(new CompanyView());
        companiesHolder.add(new CompanyView());
        companiesHolder.add(new CompanyView());

        companiesHolder.add(new CompanyView());
        companiesHolder.add(new CompanyView());
        companiesHolder.add(new CompanyView());
        companiesHolder.add(new CompanyView());


        setBorder(new WindowsBorders.DashedBorder(Color.BLACK));

        return companiesHolder;
    }

    private static class CompanyView extends JPanel {

        private JTextField name;
        private JTextField netWorth;


        CompanyView() {
            super(new GridLayout(0, 2));

            name = new JTextField();
            add(new JLabel("Company name:"));
            add(name);

            netWorth = new JTextField();
            add(new JLabel("Net worth:"));
            add(netWorth);

            //TODO add other fields

            setBorder(new WindowsBorders.DashedBorder(Color.BLACK));
        }

    }
}
