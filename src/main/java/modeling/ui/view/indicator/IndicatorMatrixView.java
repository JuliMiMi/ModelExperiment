package modeling.ui.view.indicator;

import modeling.ui.view.View;

import javax.swing.DropMode;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;

/**
 * Created by Julia on 15.05.2016.
 */
public class IndicatorMatrixView extends JPanel implements View {

    public IndicatorMatrixView() {
        super(new GridLayout(0, 1, 20, 20));
        setOpaque(true);
        updateUI();
    }

    public void setModel(Collection<CompanyModel> companyModels) {
        buildCompetitivenessMatrix(companyModels);
        buildSummaryMatrix(companyModels);
    }

    public JTable buildCompetitivenessMatrix(Collection<CompanyModel> companyModels) {
        JPanel competitivenessMatrix = new JPanel(new BorderLayout());

        Object[] header = {" ", "Indicators", "CR", " ", "CW", " ", "CM", " ", "CP", " ", "CT", " " };
        Object[][] standardData = {
                {"№", " ", "1", " ", "2", " ", "3", "4", " ", "5", " ",},
                {" ", "Levels of competitiveness", "rel.un", "point", "rel.un", "point", "rel.un", "point", "rel.un", "point", "rel.un", "point" },
                {"1", "Global leadership", "5", "100", "3.5", "100", "0.5", "100", "1", "100", "0.7", "100" },
                {"2", "World standard", "3.81", "76.2", "2.8", "80", "0.44", "88", "0.9", "90", "0.62", "88.57" },
                {"3", "National leadership", "2.94", "58.8", "2.5", "71.43", "0.36", "72", "0.78", "78", "0.46", "65.71" },
                {"4", "National standard", "2.46", "49.2", "2.4", "68.57", "0.3", "60", "0.66", "66", "0.36", "51.43" },
                {"5", "Sectoral leadership", "2.18", "43.6", "2.06", "58.86", "0.19", "38", "0.51", "51", "0.27", "38.57" },
                {"6", "Industry standards", "1.67", "33.4", "1.6", "45.71", "0.07", "14", "0.38", "38", "0.15", "21.43" },
                {"7", "Threshold level", "1.33", "26.6", "1", "28.57", "0.01", "2", "0.15", "15", "0.09", "12.86" },
        };

        DefaultTableModel defaultTableModel = new DefaultTableModel(standardData, header);


        for (CompanyModel companyModel : companyModels) {
            defaultTableModel.addRow(new Object[]{

                    defaultTableModel.getRowCount() - 1,
                    companyModel.getName(),
                    companyModel.getIndRes(),
                    companyModel.getIndRes() * 26.6 / 1.33,
                    companyModel.getIndWork(),
                    companyModel.getIndWork() * 28.57 / 1,
                    companyModel.getIndMar(),
                    companyModel.getIndMar() * 2 / 0.01,
                    companyModel.getIndRent(),
                    companyModel.getIndRent() * 15 / 0.15,
                    companyModel.getIndTime(),
                    companyModel.getIndTime() * 12.86 / 0.09

            });
        }

        JTable table = new JTable(defaultTableModel);
        table.getColumnModel().setColumnMargin(20);
        table.setSize(1000, 500);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setCellSelectionEnabled(true);
        table.setDropMode(DropMode.USE_SELECTION);
        table.updateUI();


        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        competitivenessMatrix.add(tableHeader, BorderLayout.NORTH);
        competitivenessMatrix.add(table, BorderLayout.CENTER);

        this.add(competitivenessMatrix);
        return table;
    }

    private JTable buildSummaryMatrix(Collection<CompanyModel> companyModels) {
        Object[] header = {"Levels of competitiveness" };
        return null;
    }

    @Override
    public JPanel getContent() {
        return this;
    }

    @Override
    public JMenuBar getMenu() {
        return null;
    }
}
