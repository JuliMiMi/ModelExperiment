package modeling.ui.view.indicator;

import modeling.ui.ViewHolder;
import modeling.ui.view.View;
import modeling.ui.view.ViewManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Julia on 09.05.2016.
 */
public class IndicatorMethodView extends JPanel implements View {

    public IndicatorMethodView() {
        super();
        build();
    }

    private List<CompanyView> companies = new ArrayList<>();
    private JPanel header = buildHeader();
    private JPanel companiesHolder = buildCompaniesHolder();

    private void build() {
        this.setLayout(new BorderLayout(5, 5));
        setOpaque(true);
        add(header, BorderLayout.NORTH);
        add(companiesHolder, BorderLayout.SOUTH);
        addCompany(); //default company
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());

        JLabel initialData = new JLabel("Вихідні дані");
        initialData.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        initialData.setBackground(Color.PINK);
        initialData.setFont(new Font("Cambria", Font.PLAIN, 22));
        header.add(initialData, BorderLayout.NORTH);
        initialData.setHorizontalAlignment(SwingConstants.CENTER);

        header.setBackground(Color.getHSBColor(33, 194, 27));
        return header;
    }

    private JPanel buildCompaniesHolder() {
        JPanel companiesHolder = new JPanel(new GridLayout(0, 2, 10, 10));
        companiesHolder.setBackground(Color.PINK);
        setBackground(Color.PINK);

        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        return companiesHolder;
    }

    private void addCompany() {
        CompanyView companyView = new CompanyView(companiesHolder);
        companies.add(companyView);
        companiesHolder.add(companyView);
        updateUI();
    }

    private void clearAll() {
        companies.forEach(CompanyView::clear);
    }

    private void calcAll() {
        boolean allValid = companies.stream().allMatch(companyView -> companyView.getModel().getValid());

        if (allValid) {
            companies.forEach(CompanyView::calc);
        } else {
            JOptionPane.showMessageDialog(null, "Some models invalid, please check them");
        }
    }

    private void deleteAll() {
        companies.forEach(CompanyView::delete);
        companies.clear();
    }

    private void defaultValue() {

        companies.forEach(CompanyView::delete);
        companies.clear();

        CompanyView companyView = new CompanyView(companiesHolder);
        CompanyView companyView2 = new CompanyView(companiesHolder);
        CompanyView companyView3 = new CompanyView(companiesHolder);
        CompanyView companyView4 = new CompanyView(companiesHolder);


        companyView.setName("ПАТ \"ДТЗ\"") ;
        companyView.setNetWorth("4714501");
        companyView.setContributionsBudgetSocial("121897");
        companyView.setAdvancedResources("2314151");
        companyView.setResourcesUsed("95647");
        companyView.setCostJobs("2705108");
        companyView.setProfitableMarketCapacity("25634541");
        companyView.setGrossIncomeWorker("3500080");
        companyView.setPeriodTheResultingGross("12");
        companyView.setGrossIncomeGeneral("20925348");
        companyView.setPeriodGrossHalf("42");

        companyView2.setName("ОАО \"ЮТіСТ\"");
        companyView2.setNetWorth("5962544");
        companyView2.setContributionsBudgetSocial("136451");
        companyView2.setAdvancedResources("3164519");
        companyView2.setResourcesUsed("75642");
        companyView2.setCostJobs("1944774");
        companyView2.setProfitableMarketCapacity("25684545");
        companyView2.setGrossIncomeWorker("2654535");
        companyView2.setPeriodTheResultingGross("12");
        companyView2.setGrossIncomeGeneral("36454885");
        companyView2.setPeriodGrossHalf("50");

        companyView3.setName("ПАТ \"Інтерпайн НТЗ\"");
        companyView3.setNetWorth("61254628");
        companyView3.setContributionsBudgetSocial("30045");
        companyView3.setAdvancedResources("30044512");
        companyView3.setResourcesUsed("35701");
        companyView3.setCostJobs("28051454");
        companyView3.setProfitableMarketCapacity("304134411");
        companyView3.setGrossIncomeWorker("8654345");
        companyView3.setPeriodTheResultingGross("12");
        companyView3.setGrossIncomeGeneral("30013409");
        companyView3.setPeriodGrossHalf("30");

        companyView4.setName("Маріупольський ММК");
        companyView4.setNetWorth("13354628");
        companyView4.setContributionsBudgetSocial("256452");
        companyView4.setAdvancedResources("5445420");
        companyView4.setResourcesUsed("156501");
        companyView4.setCostJobs("6551456");
        companyView4.setProfitableMarketCapacity("202013256");
        companyView4.setGrossIncomeWorker("7054145");
        companyView4.setPeriodTheResultingGross("12");
        companyView4.setGrossIncomeGeneral("24513409");
        companyView4.setPeriodGrossHalf("46");


        companies.add(companyView);
        companiesHolder.add(companyView);
        updateUI();

        companies.add(companyView2);
        companiesHolder.add(companyView2);
        updateUI();

        companies.add(companyView3);
        companiesHolder.add(companyView3);
        updateUI();

        companies.add(companyView4);
        companiesHolder.add(companyView4);
        updateUI();
    }


    @Override
    public JPanel getContent() {
        return this;
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();

      /*  JMenu method = new JMenu("Method");
        menuBar.add(method);

        JMenuItem indicator = new JMenuItem("Indicator");
        indicator.addActionListener(event -> ViewManager.getViewHolder().setView(ViewManager.getIndicatorMethodView()));
        method.add(indicator);

        JMenuItem expert = new JMenuItem("Expert");
        expert.addActionListener(event -> {
            //TODO switch into ExpertMethodView
        });
        method.add(expert);

        JMenuItem financialPosition = new JMenuItem("Financial position");
        financialPosition.addActionListener(event -> {
            //TODO switch into FinancialPositionMethodView
        });
        method.add(financialPosition);*/

        /*JMenu operation = new JMenu("Operation");
        menuBar.add(operation);*/

        JMenu operation = new JMenu("Обрати операцію");
        menuBar.add(operation);

        JMenuItem addCompany = new JMenuItem("Додати підприємство");
        addCompany.addActionListener(event -> addCompany());
        operation.add(addCompany);


        JMenuItem calculate = new JMenuItem("Розрахувати індикатори");
        calculate.addActionListener(event -> calcAll());
        operation.add(calculate);

        JMenuItem matrix = new JMenuItem("Перейти до матриці КПП");
        matrix.addActionListener(event -> {
            ViewHolder viewHolder = ViewManager.getViewHolder();
            IndicatorMatrixView indicatorMatrixView = ViewManager.getIndicatorMatrixView();

            boolean allValid = companies.stream().allMatch(companyView -> companyView.getModel().getValid());

            if (allValid) {
                calcAll();
                List<CompanyModel> models = this.companies.stream().map(CompanyView::getModel).collect(Collectors.toList());
                indicatorMatrixView.setModel(models);
                viewHolder.setView(indicatorMatrixView);
            } else {
                JOptionPane.showMessageDialog(null, "Some models invalid, please check them");
            }
        });
        operation.add(matrix);

        JMenuItem deleteAll = new JMenuItem("Видалити всі компанії");
        deleteAll.addActionListener(event -> deleteAll());
        operation.add(deleteAll);

        JMenuItem defaultVal = new JMenuItem("Значення по замовчуванням");
        defaultVal.addActionListener(event -> defaultValue());
        operation.add(defaultVal);

        return menuBar;
    }

}







