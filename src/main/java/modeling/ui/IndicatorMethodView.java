package modeling.ui;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import com.sun.java.swing.plaf.windows.WindowsBorders;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
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
        this.setLayout(new BorderLayout(5, 5));
        setOpaque(true);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildCompaniesHolder(), BorderLayout.SOUTH);


    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());

        JLabel initialData = new JLabel("Please enter the initial data");
        initialData.setBackground(Color.PINK);
        initialData.setFont(new Font("Cambria", Font.PLAIN, 22));
        //todo add listener for this button
        header.add(initialData, BorderLayout.NORTH);
        initialData.setHorizontalAlignment(SwingConstants.CENTER);


        JButton addCompany = new JButton("Add Company");
        //todo add listener for this button
        add(addCompany);

       /* JButton result = new JButton("Get results");
        //todo add listener for this button
        add(result, BorderLayout.EAST);*/

        header.setBackground(Color.PINK);


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

        companiesHolder.setBackground(Color.PINK);
        setBackground(Color.PINK);

        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));


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

        private JTextArea indicator;


        CompanyView() {

            super(new BorderLayout(5, 5));
            setBackground(Color.PINK);

            JPanel entry = new JPanel((new GridLayout(0, 4, 5, 5)));
            JPanel output = new JPanel((new GridLayout(1, 0, 5, 5)));


            JPanel buttonP = new JPanel(new GridLayout(2, 1));


            JButton clearForm = new JButton("Clear form");
            //todo add listener for this button
            buttonP.add(clearForm);

            JButton deleteCompany = new JButton("Delete Company");
            //todo add listener for this button
            buttonP.add(deleteCompany);
            setBackground(Color.PINK);

            name = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Company name:  "));
            entry.add(name);

            netWorth = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Net worth:  "));
            entry.add(netWorth);


            contributionsBudgetSocial = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Contributions to the\n budget and social funds:  "));
            entry.add(contributionsBudgetSocial);

            advancedResources = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Resources advances now:  "));
            entry.add(advancedResources);

            resourcesUsed = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Resources used by the\n enterprise:  "));
            entry.add(resourcesUsed);

            costJobs = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("The average annual cost of\n jobs:  "));
            entry.add(costJobs);

            profitableMarketCapacity = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Profitable market capacity:  "));
            entry.add(profitableMarketCapacity);

            grossIncomeWorker1 = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Gross profit per 1 worker:  "));
            entry.add(grossIncomeWorker1);

            grossIncomeGeneral = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("The total gross profit:  "));
            entry.add(grossIncomeGeneral);

            periodTheResultingGross = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("Time passed in a period\n for measuring the resulting\n gross income:  "));
            entry.add(periodTheResultingGross);

            periodGrossHalf = new JTextField();
            entry.add(LabelUtils.createWrappedLabel("The time required for half\n the increase in gross income:   "));
            entry.add(periodGrossHalf);


            // output.add(new JLabel("Results"));


           /* double netWorthD = Double.parseDouble(netWorth.getText());
            double contributionsBudgetSocialD = Double.parseDouble(contributionsBudgetSocial.getText());
            double advancedResourcesD = Double.parseDouble(advancedResources.getText());
            double resourcesUsedD = Double.parseDouble(resourcesUsed.getText());
            double costJobsD = Double.parseDouble(costJobs.getText());
            double profitableMarketCapacityD = Double.parseDouble(profitableMarketCapacity.getText());
            double grossIncomeWorker1D = Double.parseDouble(grossIncomeWorker1.getText());
            double grossIncomeGeneralD = Double.parseDouble(grossIncomeGeneral.getText());
            double periodTheResultingGrossD = Double.parseDouble(periodTheResultingGross.getText());
            double periodGrossHalfD = Double.parseDouble(periodGrossHalf.getText());*/


            double ind1 = 5;
            double ind2 = 6;
            double ind3 = 4;
            double ind4 = 2;
            double ind5 = 1;

          /*  double ind1 = (netWorthD / contributionsBudgetSocialD) / (advancedResourcesD / resourcesUsedD);
            double ind2 = netWorthD / costJobsD;
            double ind3 = netWorthD / profitableMarketCapacityD;
            double ind4 = grossIncomeWorker1D / grossIncomeGeneralD;
            double ind5 = periodTheResultingGrossD / periodGrossHalfD;*/

            StringBuilder res = new StringBuilder();
            res.append(" \n Indicator of resource use:    ").append(ind1).append("\n");
            res.append(" Indicator of use of work:    ").append(ind2).append("\n");
            res.append(" Indicator market size:    ").append(ind3).append("\n");
            res.append(" Profitability indicator:    ").append(ind4).append("\n");
            res.append(" Indicator time use resource:    ").append(ind5).append("\n");

            indicator = new JTextArea();
            indicator.setText(res.toString());
            indicator.setFont(new Font("Cambria", Font.PLAIN, 22));

            output.add(indicator);

            add(buttonP, BorderLayout.SOUTH);
            add(entry, BorderLayout.CENTER);
            add(output, BorderLayout.NORTH);


            entry.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)));


            output.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                    BorderFactory.createEmptyBorder(4, 4, 4, 4)));

            setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(Color.WHITE, 4, true),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }

    }
}
