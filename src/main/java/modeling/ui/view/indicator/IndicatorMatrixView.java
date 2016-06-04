package modeling.ui.view.indicator;

import modeling.ui.util.ExportUtils;
import modeling.ui.view.View;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Julia on 15.05.2016.
 */
public class IndicatorMatrixView extends JPanel implements View {

    private Collection<CompanyModel> companyModels;

    public IndicatorMatrixView() {
        super(new BorderLayout(10, 10));
        setOpaque(true);
        setBackground(Color.PINK);
        updateUI();
    }

    public void setModel(Collection<CompanyModel> companyModels) {
        this.companyModels = companyModels;
        buildCompetitivenessMatrix(companyModels);
        buildSummaryMatrix(companyModels);
        buildChart(companyModels);
        this.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
    }

    public JTable buildCompetitivenessMatrix(Collection<CompanyModel> companyModels) {
        JPanel competitivenessMatrix = new JPanel(new BorderLayout());
        Object[] header = {"№", "Стандарти", "КР", " ", "КП", " ", "КЄ", " ", "КД", " ", "КЧ", " "};
        Object[][] standardData = {
                {" ", "Ревень КПП", "від.од", "бал", "від.од", "бал", "від.од", "бал", "від.од", "бал", "від.од", "бал"},
                {"1", "Світове лідерство", "5", "100", "3.5", "100", "0.5", "100", "1", "100", "0.7", "100"},
                {"2", "Світовий стандарт", "3.81", "76.2", "2.8", "80", "0.44", "88", "0.9", "90", "0.62", "88.57"},
                {"3", "Націлнальне лідерство", "2.94", "58.8", "2.5", "71.43", "0.36", "72", "0.78", "78", "0.46", "65.71"},
                {"4", "Націоналний стандарт", "2.46", "49.2", "2.4", "68.57", "0.3", "60", "0.66", "66", "0.36", "51.43"},
                {"5", "Галузеве лідерство", "2.18", "43.6", "2.06", "58.86", "0.19", "38", "0.51", "51", "0.27", "38.57"},
                {"6", "Галузевий стандарт", "1.67", "33.4", "1.6", "45.71", "0.07", "14", "0.38", "38", "0.15", "21.43"},
                {"7", "Пороговий рівень", "1.33", "26.6", "1", "28.57", "0.01", "2", "0.15", "15", "0.09", "12.86"},
        };

        DefaultTableModel defaultTableModel = new DefaultTableModel(standardData, header);
        for (CompanyModel companyModel : companyModels) {
            defaultTableModel.addRow(new Object[]{
                    defaultTableModel.getRowCount(),
                    companyModel.getName(),
                    String.format("%.2f", companyModel.getIndRes()),
                    String.format("%.2f", companyModel.getIndRes() * 26.6 / 1.33),
                    String.format("%.2f", companyModel.getIndWork()),
                    String.format("%.2f", companyModel.getIndWork() * 28.57 / 1),
                    String.format("%.2f", companyModel.getIndMar()),
                    String.format("%.2f", companyModel.getIndMar() * 2 / 0.01),
                    String.format("%.2f", companyModel.getIndRent()),
                    String.format("%.2f", companyModel.getIndRent() * 15 / 0.15),
                    String.format("%.2f", companyModel.getIndTime()),
                    String.format("%.2f", companyModel.getIndTime() * 12.86 / 0.09)
            });
        }


        JTable table = new JTable(defaultTableModel);
        table.getColumnModel().setColumnMargin(10);
        table.getColumnModel().getColumn(0).setMinWidth(10);
        table.getColumnModel().getColumn(1).setMinWidth(170);
        table.setFont(new Font("Cambria", Font.PLAIN, 14));
        table.updateUI();


        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        competitivenessMatrix.add(tableHeader, BorderLayout.NORTH);
        competitivenessMatrix.add(table, BorderLayout.SOUTH);
        this.add(competitivenessMatrix, BorderLayout.NORTH);
        return table;
    }


    private void buildSummaryMatrix(Collection<CompanyModel> companyModels) {
        JPanel summaryMatrix = new JPanel(new BorderLayout());
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        DefaultTableModel resultTableModel = new DefaultTableModel();
        resultTableModel.addColumn("Рівень КПП", new Object[]{
                "Світове лідерство", "Світовий стандарт", "Національне лідерство", "Національний стандарт", "Галузеве лідерство", "Галузевий стандарт", "Пороговий рівень"
        });

        for (CompanyModel companyModel : companyModels) {
            resultTableModel.addColumn(companyModel.getName(), new Object[]{
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (100 + 100 + 100 + 100 + 100)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (76.2 + 80 + 88 + 90 + 88.57)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (58.8 + 71.43 + 72 + 78 + 65.71)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (49.2 + 68.57 + 60 + 66 + 51.43)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (43.6 + 58.86 + 38 + 51 + 38.57)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (33.4 + 45.71 + 14 + 38 + 21.43)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (26.6 + 28.57 + 2 + 15 + 12.86))
            });

        }

        JTable table = new JTable(resultTableModel);
        table.setBackground(Color.PINK);
        table.getColumnModel().setColumnMargin(10);
        table.getColumnModel().getColumn(0).setMinWidth(170);
        table.setFont(new Font("Cambria", Font.PLAIN, 14));
        table.updateUI();

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        summaryMatrix.add(tableHeader, BorderLayout.NORTH);
        summaryMatrix.add(table, BorderLayout.CENTER);
        // return table;
        this.add(summaryMatrix, BorderLayout.CENTER);


        StringBuilder levelSearch = new StringBuilder();
        JTextArea levelS = new JTextArea();
        levelS.setFont(new Font("Cambria", Font.PLAIN, 16));
        String level = " ";
        JPanel levelSo = new JPanel(new BorderLayout());
        this.add(levelSo, BorderLayout.EAST);
        DefaultTableModel leveles = new DefaultTableModel();
        for (CompanyModel companyModel : companyModels) {

            leveles.addColumn(companyModel.getName(), new Object[]{String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (100 + 100 + 100 + 100 + 100)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (76.2 + 80 + 88 + 90 + 88.57)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (58.8 + 71.43 + 72 + 78 + 65.71)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (49.2 + 68.57 + 60 + 66 + 51.43)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (43.6 + 58.86 + 38 + 51 + 38.57)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (33.4 + 45.71 + 14 + 38 + 21.43)),
                    String.format("%.2f", ((companyModel.getIndRes() * 26.6 / 1.33) + (companyModel.getIndWork() * 28.57 / 1) + (companyModel.getIndMar() * 2 / 0.01) + (companyModel.getIndRent() * 15 / 0.15) + (companyModel.getIndTime() * 12.86 / 0.09)) / (26.6 + 28.57 + 2 + 15 + 12.86))
            });


            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86) > 1) {
                level = "Пороговий рівень";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43) > 1) {
                level = "Галузевий стандарт";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 58.86 + 38 + 51 + 38.57) > 1) {
                level = "Галузеве лідерство";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (49.2 + 68.57 + 60 + 66 + 51.43) > 1) {
                level = "Національний стандарт";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (58.8 + 71.43 + 72 + 78 + 65.71) > 1) {
                level = "Національне лідерство";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (76.2 + 80 + 88 + 90 + 88.57) > 1) {
                level = "Світовий стандарт";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (100 + 100 + 100 + 100 + 100) > 1) {
                level = "Світове лідерство";

            }

            levelSearch.append(" Підприємство ").append(companyModel.getName()).append(" відноситься \nдо рівня ").append(" ").append(level).append(" \n");
            levelSearch.append("\n");
            levelS.setText(levelSearch.toString());
            levelS.setVisible(true);
            levelSo.add(levelS, BorderLayout.WEST);
            levelSo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createBevelBorder(BevelBorder.RAISED),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        }
    }

    private void buildChart(Collection<CompanyModel> companyModels) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        JTextArea conclusion = new JTextArea();
        conclusion.setFont(new Font("Cambria", Font.PLAIN, 20));
        conclusion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JPanel gistogr = new JPanel(new BorderLayout(10, 10));
        gistogr.setBackground((Color.pink));
        this.add(gistogr, BorderLayout.SOUTH);

        Integer xValue = 0;
        for (CompanyModel companyModel : companyModels) {

            final XYSeries series = new XYSeries(companyModel.getName());
            Double y = 0.0;

            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (100 + 100 + 100 + 100 + 100) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (100 + 100 + 100 + 100 + 100);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (76.2 + 80 + 88 + 90 + 88.57) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (76.2 + 80 + 88 + 90 + 88.57);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (58.8 + 71.43 + 72 + 78 + 65.71) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (58.8 + 71.43 + 72 + 78 + 65.71);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (49.2 + 68.57 + 60 + 66 + 51.43) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (49.2 + 68.57 + 60 + 66 + 51.43);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 58.86 + 38 + 51 + 38.57) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 58.86 + 38 + 51 + 38.57);
            }

            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86) < 1) {
                y = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86);
            }


            xValue += 5;
            series.add(xValue, y);
            dataset.addSeries(series);
        }
        JFreeChart chart = ChartFactory.createXYBarChart("Рейтинг підприємств", "Підприємства", true, "Значення", dataset, PlotOrientation.VERTICAL, true, false, true);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));


        gistogr.add(chartPanel, BorderLayout.CENTER);
        gistogr.add(conclusion, BorderLayout.SOUTH);


        StringBuilder conculisionBuild = new StringBuilder();
        Double max = 0.0;
        Double best = 0.0;
        String nameBest = " ";
        for (CompanyModel companyModel : companyModels) {


            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 58.86 + 38 + 51 + 38.57) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 58.86 + 38 + 51 + 38.57);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (49.2 + 68.57 + 60 + 66 + 51.43) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (49.2 + 68.57 + 60 + 66 + 51.43);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (58.8 + 71.43 + 72 + 78 + 65.71) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (58.8 + 71.43 + 72 + 78 + 65.71);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (76.2 + 80 + 88 + 90 + 88.57) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (76.2 + 80 + 88 + 90 + 88.57);
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (100 + 100 + 100 + 100 + 100) < 1) {
                best = (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (100 + 100 + 100 + 100 + 100);
            }

            if (best > max) {
                max = best;
                nameBest = companyModel.getName();
            }


        }
        conculisionBuild.append(" Підприємство ").append(nameBest).append(" має кращі показники конкурентоспроможності потенціалу.");
        conclusion.setText(conculisionBuild.toString());
    }


    @Override
    public JPanel getContent() {
        return this;
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu operation = new JMenu("Экспорт");
        operation.setFont(new Font("Cambria", Font.BOLD, 16));
        menuBar.add(operation);

        JMenuItem addCompany = new JMenuItem("В Excel (.xlsx)");
        addCompany.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.WHITE, 4, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        addCompany.setFont(new Font("Aria", Font.BOLD, 16));
        addCompany.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Excel (.xlsx)", "xlsx"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filename = fileChooser.getSelectedFile().getPath();
                    if (!filename.endsWith(".xlsx")) {
                        filename = filename + ".xlsx";
                    }

                    FileOutputStream out = new FileOutputStream(filename);
                    ExportUtils.exportToExcel(this.companyModels, out);
                    JOptionPane.showMessageDialog(this, "Export done at " + filename);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
                }
            }
        });

        operation.add(addCompany);

        return menuBar;
    }


}

