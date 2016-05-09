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
        private JTextField contributionsBudgetSocial;
        private JTextField advancedResources;
        private JTextField resourcesUsed;
        private JTextField costJobs;
        private JTextField profitableMarketCapacity;
        private JTextField grossIncomeWorker1;
        private JTextField grossIncomeGeneral;
        private JTextField periodTheResultingGross;
        private JTextField periodGrossHalf;


        CompanyView() {

            super(new GridLayout(0, 2, 10, 5));

            name = new JTextField();
            add(LabelUtils.createWrappedLabel("Company name:  "));
            add(name);

            netWorth = new JTextField();
            add(LabelUtils.createWrappedLabel("Net worth:  "));
            add(netWorth);

            contributionsBudgetSocial = new JTextField();
            add(LabelUtils.createWrappedLabel("Contributions to the\n budget and social funds:  "));
            add(contributionsBudgetSocial);

            advancedResources = new JTextField();
            add(LabelUtils.createWrappedLabel("Resources advances now:  "));
            add(advancedResources);

            resourcesUsed = new JTextField();
            add(LabelUtils.createWrappedLabel("Resources used by\n the enterprise:  "));
            add(resourcesUsed);

            costJobs = new JTextField();
            add(LabelUtils.createWrappedLabel("The average annual cost of\n jobs:  "));
            add(costJobs);

            profitableMarketCapacity = new JTextField();
            add(LabelUtils.createWrappedLabel("Profitable market capacity:  "));
            add(profitableMarketCapacity);

            grossIncomeWorker1 = new JTextField();
            add(LabelUtils.createWrappedLabel("Gross profit per 1 worker:  "));
            add(grossIncomeWorker1);

            grossIncomeGeneral = new JTextField();
            add(LabelUtils.createWrappedLabel("The total gross profit:  "));
            add(grossIncomeGeneral);

            periodTheResultingGross = new JTextField();
            add(LabelUtils.createWrappedLabel("Time passed in a period\n for measuring the resulting\n gross income:  "));
            add(periodTheResultingGross);

            periodGrossHalf = new JTextField();
            add(LabelUtils.createWrappedLabel("The time required for half\n the increase in gross income:   "));
            add(periodGrossHalf);

            setBorder(new WindowsBorders.DashedBorder(Color.BLACK));
        }

    }
}
