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
import java.util.Iterator;
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

        header.setBackground(Color.getHSBColor(45, 256, 7));
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
        CompanyView companyView = new CompanyView(this);
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
        Iterator<CompanyView> iterator = companies.iterator();
        while (iterator.hasNext()) {
            CompanyView next = iterator.next();
            companiesHolder.remove(next);
            next.updateUI();
            iterator.remove();
        }
        updateUI();
        companies.forEach(CompanyView::delete);
        companies.clear();
    }

    public void deleteCompany(CompanyView companyView) {
        companiesHolder.remove(companyView);
        companyView.updateUI();

        companies.remove(companyView);
        updateUI();
    }

    private void defaultValue() {

        Iterator<CompanyView> iterator = companies.iterator();
        while (iterator.hasNext()) {
            CompanyView next = iterator.next();
            companiesHolder.remove(next);
            next.updateUI();
            iterator.remove();
        }

        CompanyView companyView = new CompanyView(this);
        CompanyView companyView2 = new CompanyView(this);
        CompanyView companyView3 = new CompanyView(this);
        CompanyView companyView4 = new CompanyView(this);


        companyView.setName("ПАТ \"ДТЗ\"");
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

        companyView4.setName("Маріупольський МК");
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


        companies.add(companyView3);
        companiesHolder.add(companyView3);
        updateUI();

        companies.add(companyView);
        companiesHolder.add(companyView);
        updateUI();

        companies.add(companyView2);
        companiesHolder.add(companyView2);
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

        JMenu operation = new JMenu("Меню");
        operation.setFont(new Font("Cambria", Font.BOLD, 16));
        menuBar.add(operation);

        JMenuItem addCompany = new JMenuItem("Додати підприємство");
        addCompany.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        addCompany.setFont(new Font("Aria", Font.BOLD, 16));
        addCompany.addActionListener(event -> addCompany());
        operation.add(addCompany);


        JMenuItem calculate = new JMenuItem("Розрахувати індикатори");
        calculate.setFont(new Font("Aria", Font.BOLD, 16));
        calculate.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        calculate.addActionListener(event -> calcAll());
        operation.add(calculate);

        JMenuItem matrix = new JMenuItem("Перейти до матриці КПП");
        matrix.setFont(new Font("Aria", Font.BOLD, 16));
        matrix.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
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
        deleteAll.setFont(new Font("Aria", Font.BOLD, 16));
        deleteAll.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        deleteAll.addActionListener(event -> deleteAll());
        operation.add(deleteAll);

        JMenuItem defaultVal = new JMenuItem("Значення по замовчуванням");
        defaultVal.setFont(new Font("Aria", Font.BOLD, 16));
        defaultVal.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        defaultVal.addActionListener(event -> defaultValue());
        operation.add(defaultVal);

        return menuBar;
    }

}







