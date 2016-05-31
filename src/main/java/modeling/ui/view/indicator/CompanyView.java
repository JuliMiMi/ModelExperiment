package modeling.ui.view.indicator;

import modeling.ui.util.FieldUtils;
import modeling.ui.util.LabelUtils;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Created by Oleksii Martyniuk on 14.05.2016.
 */
public class CompanyView extends JPanel {

    private CompanyModel model = new CompanyModel();

    private IndicatorMethodView parent;

    private JTextField name;
    private JTextField netWorth;
    private JTextField contributionsBudgetSocial;
    private JTextField advancedResources;
    private JTextField resourcesUsed;
    private JTextField costJobs;
    private JTextField profitableMarketCapacity;
    private JTextField grossIncomeWorker;
    private JTextField grossIncomeGeneral;
    private JTextField periodTheResultingGross;
    private JTextField periodGrossHalf;

    private JTextArea indicator;

    private JPanel output;


    public void setName(String name) {
        this.name.setText(name);
    }

    public void setNetWorth(String netWorth) {
        this.netWorth.setText(String.valueOf(netWorth));
    }

    public void setContributionsBudgetSocial(String contributionsBudgetSocial) {
        this.contributionsBudgetSocial.setText(String.valueOf(contributionsBudgetSocial));
    }

    public void setAdvancedResources(String advancedResources) {
        this.advancedResources.setText(String.valueOf(advancedResources));
    }

    public void setResourcesUsed(String resourcesUsed) {
        this.resourcesUsed.setText(String.valueOf(resourcesUsed));
    }

    public void setCostJobs(String costJobs) {
        this.costJobs.setText(String.valueOf(costJobs));
    }

    public void setProfitableMarketCapacity(String profitableMarketCapacity) {
        this.profitableMarketCapacity.setText(String.valueOf(profitableMarketCapacity));
    }

    public void setGrossIncomeWorker(String grossIncomeWorker) {
        this.grossIncomeWorker.setText(String.valueOf(grossIncomeWorker));
    }

    public void setGrossIncomeGeneral(String grossIncomeGeneral) {
        this.grossIncomeGeneral.setText(String.valueOf(grossIncomeGeneral));
    }

    public void setPeriodTheResultingGross(String periodTheResultingGross) {
        this.periodTheResultingGross.setText(String.valueOf(periodTheResultingGross));
    }

    public void setPeriodGrossHalf(String periodGrossHalf) {
        this.periodGrossHalf.setText(String.valueOf(periodGrossHalf));
    }


    public CompanyView(IndicatorMethodView parent) {
        super(new BorderLayout(5, 5));

        this.parent = parent;

        buildCommon();
        buildInput();
        buildOutput();
    }


    private void buildCommon() {
        JPanel buttons = new JPanel(new GridLayout(1, 1));
        add(buttons, BorderLayout.SOUTH);

        JButton clearButton = new JButton("Очистити форму");
        clearButton.addActionListener(e -> clear());
        buttons.add(clearButton);

        JButton deleteCompany = new JButton("Видалити компанію");
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
        name.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Назва підприємства:  "));
        input.add(name);
        FieldUtils.bindStringField(name, model::setName, model::setValid);

        netWorth = new JTextField();
        netWorth.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Чистий прибуток:  "));
        input.add(netWorth);
        FieldUtils.bindDoubleField(netWorth, model::setNetWorth, model::setValid);

        contributionsBudgetSocial = new JTextField();
        contributionsBudgetSocial.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Відрахування в бюджет \nі соціальні фонди:  "));
        input.add(contributionsBudgetSocial);
        FieldUtils.bindDoubleField(contributionsBudgetSocial, model::setContributionsBudgetSocial, model::setValid);

        advancedResources = new JTextField();
        advancedResources.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Ресурси, авансовіні  \nпідприємством:  "));
        input.add(advancedResources);
        FieldUtils.bindDoubleField(advancedResources, model::setAdvancedResources, model::setValid);

        resourcesUsed = new JTextField();
        resourcesUsed.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Ресурси, використані  \nпідприємством:  "));
        input.add(resourcesUsed);
        FieldUtils.bindDoubleField(resourcesUsed, model::setResourcesUsed, model::setValid);

        costJobs = new JTextField();
        costJobs.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Вартість робочих місць  \n(середньорічна):  "));
        input.add(costJobs);
        FieldUtils.bindDoubleField(costJobs, model::setCostJobs, model::setValid);

        profitableMarketCapacity = new JTextField();
        profitableMarketCapacity.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Прибуткова місткість\n ринку:  "));
        input.add(profitableMarketCapacity);
        FieldUtils.bindDoubleField(profitableMarketCapacity, model::setProfitableMarketCapacity, model::setValid);

        grossIncomeWorker = new JTextField();
        grossIncomeWorker.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Валовий дохід в розрахунку\n на 1 робітника, включаючи \nвласників:  "));
        input.add(grossIncomeWorker);
        FieldUtils.bindDoubleField(grossIncomeWorker, model::setGrossIncomeWorker, model::setValid);

        periodTheResultingGross = new JTextField();
        periodTheResultingGross.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Час, прийнятий в якості\n періоду для  виміру отри-\nманого валового доходу:  "));
        input.add(periodTheResultingGross);
        FieldUtils.bindDoubleField(periodTheResultingGross, model::setPeriodTheResultingGross, model::setValid);

        grossIncomeGeneral = new JTextField();
        grossIncomeGeneral.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Валовий дохід, \nщо формується в eкономіці \nкраїни в цілому:  "));
        input.add(grossIncomeGeneral);
        FieldUtils.bindDoubleField(grossIncomeGeneral, model::setGrossIncomeGeneral, model::setValid);


        periodGrossHalf = new JTextField();
        periodGrossHalf.setFont(new Font("Cambria", Font.PLAIN, 16));
        input.add(LabelUtils.createWrappedLabel("Час, необхідний \nдля  збільшення \nйого вдвічі:   "));
        input.add(periodGrossHalf);
        FieldUtils.bindDoubleField(periodGrossHalf, model::setPeriodGrossHalf, model::setValid);

        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        add(input, BorderLayout.CENTER);
    }

    private void buildOutput() {
        output = new JPanel((new GridLayout(1, 0, 5, 5)));
        output.setVisible(false);
        output.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));

        indicator = new JTextArea();
        indicator.setFont(new Font("Cambria", Font.PLAIN, 18));

        output.add(indicator);
        add(output, BorderLayout.NORTH);
    }

    public boolean calc() {
        if (!model.getValid()) {
            //todo show message about something invalid
            return false;
        }

        model.calc();


        StringBuilder result = new StringBuilder();

        result.append(" Індикатор використання ресурсів:    ").append(String.format("%.2f", model.getIndRes())).append("\n");
        result.append(" Індикатор використання трудового потенціалу:    ").append(String.format("%.2f", model.getIndWork())).append("\n");
        result.append(" Індикатор ємності ринку:    ").append(String.format("%.2f", model.getIndMar())).append("\n");
        result.append(" Індикатор прибутковості:    ").append(String.format("%.2f", model.getIndRent())).append("\n");
        result.append(" Індикатор використання часового ресурсу:    ").append(String.format("%.2f", model.getIndTime()));

        indicator.setText(result.toString());
        output.setVisible(true);
        return true;
    }

    public void delete() {
        parent.deleteCompany(this);
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
                grossIncomeWorker,
                grossIncomeGeneral,
                periodTheResultingGross,
                periodGrossHalf,
                indicator
        );

        output.setVisible(false);
        updateUI();
    }

    public CompanyModel getModel() {
        return model;
    }


}