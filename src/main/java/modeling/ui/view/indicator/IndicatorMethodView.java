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
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
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

        JLabel initialData = new JLabel("Please enter the initial data");
        initialData.setBackground(Color.PINK);
        initialData.setFont(new Font("Cambria", Font.PLAIN, 22));
        header.add(initialData, BorderLayout.NORTH);
        initialData.setHorizontalAlignment(SwingConstants.CENTER);

        header.setBackground(Color.PINK);
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

    @Override
    public JPanel getContent() {
        return this;
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu method = new JMenu("Method");
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
        method.add(financialPosition);

        JMenu operation = new JMenu("Operation");
        menuBar.add(operation);

        JMenuItem addCompany = new JMenuItem("Add company");
        addCompany.addActionListener(event -> addCompany());
        operation.add(addCompany);

        JMenuItem clearAll = new JMenuItem("Clear all");
        clearAll.addActionListener(event -> clearAll());
        operation.add(clearAll);

        JMenuItem calculate = new JMenuItem("Calculate");
        calculate.addActionListener(event -> calcAll());
        operation.add(calculate);

        JMenuItem matrix = new JMenuItem("Show matrix");
        matrix.addActionListener(event -> {
            ViewHolder viewHolder = ViewManager.getViewHolder();
            IndicatorMatrixView indicatorMatrixView = ViewManager.getIndicatorMatrixView();

            boolean allValid = companies.stream().allMatch(companyView -> companyView.getModel().getValid());

            if (allValid) {
                List<CompanyModel> models = this.companies.stream().map(CompanyView::getModel).collect(Collectors.toList());
                indicatorMatrixView.buildCompetitivenessMatrix(models);
                viewHolder.setView(indicatorMatrixView);
            } else {
                JOptionPane.showMessageDialog(null, "Some models invalid, please check them");
            }
        });
        operation.add(matrix);

        JMenuItem deleteAll = new JMenuItem("Delete all");
        deleteAll.addActionListener(event -> deleteAll());
        operation.add(deleteAll);

        return menuBar;
    }

}







