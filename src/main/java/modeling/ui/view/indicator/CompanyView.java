package modeling.ui.view.indicator;

import modeling.ui.util.FieldUtils;
import modeling.ui.util.LabelUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Oleksii Martyniuk on 14.05.2016.
 */
public class CompanyView extends JPanel {

    private JPanel parent;

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

    private JPanel output;

    public CompanyView(JPanel parent) {
        super(new BorderLayout(5, 5));
        this.parent = parent;

        buildCommon();
        buildInput();
        buildOutput();
    }

    private void buildCommon() {
        JPanel buttons = new JPanel(new GridLayout(2, 1));
        add(buttons, BorderLayout.SOUTH);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clear());
        buttons.add(clearButton);

        JButton deleteCompany = new JButton("Delete Company");
        buttons.add(deleteCompany);
        deleteCompany.addActionListener(e -> delete());

        setBackground(Color.PINK);
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }

    private void buildInput() {
        JPanel input = new JPanel((new GridLayout(0, 4, 5, 5)));
        name = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Company name:  "));
        input.add(name);

        netWorth = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Net worth:  "));
        input.add(netWorth);

        contributionsBudgetSocial = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Contributions to the\n budget and social funds:  "));
        input.add(contributionsBudgetSocial);

        advancedResources = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Resources advances now:  "));
        input.add(advancedResources);

        resourcesUsed = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Resources used by the\n enterprise:  "));
        input.add(resourcesUsed);

        costJobs = new JTextField();
        input.add(LabelUtils.createWrappedLabel("The average annual cost of\n jobs:  "));
        input.add(costJobs);

        profitableMarketCapacity = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Profitable market capacity:  "));
        input.add(profitableMarketCapacity);

        grossIncomeWorker1 = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Gross profit per 1 worker:  "));
        input.add(grossIncomeWorker1);

        grossIncomeGeneral = new JTextField();
        input.add(LabelUtils.createWrappedLabel("The total gross profit:  "));
        input.add(grossIncomeGeneral);

        periodTheResultingGross = new JTextField();
        input.add(LabelUtils.createWrappedLabel("Time passed in a period\n for measuring the resulting\n gross income:  "));
        input.add(periodTheResultingGross);

        periodGrossHalf = new JTextField();
        input.add(LabelUtils.createWrappedLabel("The time required for half\n the increase in gross income:   "));
        input.add(periodGrossHalf);


        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        add(input, BorderLayout.CENTER);

    }

    private void buildOutput() {
        output = new JPanel((new GridLayout(1, 0, 5, 5)));
        output.setVisible(false);
        output.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));

        indicator = new JTextArea();
        indicator.setFont(new Font("Cambria", Font.PLAIN, 22));

        output.add(indicator);
        add(output, BorderLayout.NORTH);
    }

    public void calc() {
        boolean valid = FieldUtils.validateDoubleFields(
                netWorth,
                contributionsBudgetSocial,
                advancedResources,
                resourcesUsed,
                costJobs,
                profitableMarketCapacity,
                grossIncomeWorker1,
                grossIncomeGeneral,
                periodTheResultingGross,
                periodGrossHalf
        );

        if(!valid){
            //todo show message about something invalid
            return;
        }

        double netWorthD = FieldUtils.parseDoubleField(netWorth);
        double contributionsBudgetSocialD = FieldUtils.parseDoubleField(contributionsBudgetSocial);
        double advancedResourcesD = FieldUtils.parseDoubleField(advancedResources);
        double resourcesUsedD = FieldUtils.parseDoubleField(resourcesUsed);
        double costJobsD = FieldUtils.parseDoubleField(costJobs);
        double profitableMarketCapacityD = FieldUtils.parseDoubleField(profitableMarketCapacity);
        double grossIncomeWorker1D = FieldUtils.parseDoubleField(grossIncomeWorker1);
        double grossIncomeGeneralD = FieldUtils.parseDoubleField(grossIncomeGeneral);
        double periodTheResultingGrossD = FieldUtils.parseDoubleField(periodTheResultingGross);
        double periodGrossHalfD = FieldUtils.parseDoubleField(periodGrossHalf);

        double ind1 = (netWorthD / contributionsBudgetSocialD) / (advancedResourcesD / resourcesUsedD);
        double ind2 = netWorthD / costJobsD;
        double ind3 = netWorthD / profitableMarketCapacityD;
        double ind4 = grossIncomeWorker1D / grossIncomeGeneralD;
        double ind5 = periodTheResultingGrossD / periodGrossHalfD;

        StringBuilder result = new StringBuilder();
        result.append(" Indicator of resource use:    ").append(ind1).append("\n");
        result.append(" Indicator of use of work:    ").append(ind2).append("\n");
        result.append(" Indicator market size:    ").append(ind3).append("\n");
        result.append(" Profitability indicator:    ").append(ind4).append("\n");
        result.append(" Indicator time use resource:    ").append(ind5).append("\n");

        indicator.setText(result.toString());
        output.setVisible(true);
    }

    public void delete() {
        parent.remove(this);
        parent.updateUI();
        updateUI();
    }

    public void clear() {
        FieldUtils.clearFields(
                name,
                netWorth,
                contributionsBudgetSocial,
                advancedResources,
                resourcesUsed,
                costJobs,
                profitableMarketCapacity,
                grossIncomeWorker1,
                grossIncomeGeneral,
                periodTheResultingGross,
                periodGrossHalf,
                indicator
        );

        output.setVisible(false);
        updateUI();
    }

}