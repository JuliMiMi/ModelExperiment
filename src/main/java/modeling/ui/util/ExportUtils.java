package modeling.ui.util;

import modeling.ui.view.indicator.CompanyModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

/**
 * Created by Oleksii Martyniuk on 02.06.2016.
 */
public class ExportUtils {

    private static final Integer DEFAULT_COLUMN_WIDTH = 256 * 20;

    private static void generateIndicatorSheet(Workbook workbook, Collection<CompanyModel> companyModels) {
        Sheet sheet = workbook.createSheet("Iндикатори");

        //create header
        createRow(sheet, 0,
                "Назва підприємства",
                "Чистий прибуток",
                "Відрахування в бюджет і соціальні фонди",
                "Ресурси, авансовіні підприємством",
                "Ресурси, використані підприємством",
                "Вартість робочих місць (середньорічна)",
                "Прибуткова місткість ринку",
                "Валовий дохід в розрахунку на 1 робітника, включаючи власників",
                "Час, прийнятий в якості періоду для  виміру отриманого валового доходу",
                "Валовий дохід, що формується в eкономіці країни в цілому:",
                "Час, необхідний для  збільшення його вдвічі",

                "Індикатор використання ресурсів",
                "Індикатор використання трудового потенціалу",
                "Індикатор ємності ринку",
                "Індикатор прибутковості",
                "Індикатор використання часового ресурсу");


        int rowNum = 1;
        for (CompanyModel companyModel : companyModels) {
            createRow(sheet, rowNum,
                    companyModel.getName(),
                    companyModel.getNetWorth(),
                    companyModel.getContributionsBudgetSocial(),
                    companyModel.getAdvancedResources(),
                    companyModel.getResourcesUsed(),
                    companyModel.getCostJobs(),
                    companyModel.getProfitableMarketCapacity(),
                    companyModel.getGrossIncomeWorker(),
                    companyModel.getPeriodTheResultingGross(),
                    companyModel.getGrossIncomeGeneral(),
                    companyModel.getPeriodGrossHalf(),

                    companyModel.getIndRes(),
                    companyModel.getIndWork(),
                    companyModel.getIndMar(),
                    companyModel.getIndRent(),
                    companyModel.getIndTime());
            rowNum++;
        }

        setDefaultColumnWidth(sheet, 20);
    }

    private static void generateMatrixSheet(Workbook workbook, Collection<CompanyModel> companyModels) {
        Sheet sheet = workbook.createSheet("Матриця КПП");

        //first table - predefined data
        createRow(sheet, 0, "Стандарти", "КР", " ", "КП", " ", "КЄ", " ", "КД", " ", "КЧ", " ");
        createRow(sheet, 1, "Ревень КПП", "від.од", "бал", "від,од", "бал", "від.од", "бал", "від.од", "бал", "від.од", "бал");
        createRow(sheet, 2, "Світове лідерство", 5, 100, 3.5, 100, 0.5, 100, 1, 100, 0.7, 100);
        createRow(sheet, 3, "Світовий стандарт", 3.81, 76.2, 2.8, 80, 0.44, 88, 0.9, 90, 0.62, 88.57);
        createRow(sheet, 4, "Націлнальне лідерство", 2.94, 58.8, 2.5, 71.43, 0.36, 72, 0.78, 78, 0.46, 65.71);
        createRow(sheet, 5, "Націоналний стандарт", 2.46, 49.2, 2.4, 68.57, 0.3, 60, 0.66, 66, 0.36, 51.43);
        createRow(sheet, 6, "Галузеве лідерство", 2.18, 43.6, 2.06, 58.86, 0.19, 38, 0.51, 51, 0.27, 38.57);
        createRow(sheet, 7, "Галузевий стандарт", 1.67, 33.4, 1.6, 45.71, 0.07, 14, 0.38, 38, 0.15, 21.43);
        createRow(sheet, 8, "Пороговий рівень", 1.33, 26.6, 1, 28.57, 0.01, 2, 0.15, 15, 0.09, 12.86);
        //first table - dynamic data
        int rowNum = 9;

        for (CompanyModel companyModel : companyModels) {
            createRow(sheet, rowNum, companyModel.getName(),
                    companyModel.getIndRes(),
                    companyModel.getIndRes() * 26.6 / 1.33,
                    companyModel.getIndWork(),
                    companyModel.getIndWork() * 28.57 / 1,
                    companyModel.getIndMar(),
                    companyModel.getIndMar() * 2 / 0.01,
                    companyModel.getIndRent(),
                    companyModel.getIndRent() * 15 / 0.15,
                    companyModel.getIndTime(),
                    companyModel.getIndTime() * 12.86 / 0.09);
            rowNum++;
        }


        //second table - predefined data
        createColumn(sheet, 0, 0, "Рівень КПП", "Світове лідерство", "Світовий стандарт", "Національне лідерство", "Національний стандарт", "Галузеве лідерство", "Галузевий стандарт", "Пороговий рівень");

        //second table - dynamic data
        int colNum = 0;
        int yOffset = 10 + companyModels.size();
        for (CompanyModel companyModel : companyModels) {
            createColumn(sheet, yOffset, colNum,
                    companyModel.getName(),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (100 + 100 + 100 + 100 + 100),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (76.2 + 80 + 88 + 90 + 88.57),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (58.8 + 71.43 + 72 + 78 + 65.71),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (49.2 + 68.57 + 60 + 66 + 51.43),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 57.86 + 38 + 51 + 38 + 57),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43),
                    (companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86)
            );
            colNum++;
        }


        //Message in merged region
        StringBuilder levelSearch = new StringBuilder();
        String level = null;
        for (CompanyModel companyModel : companyModels) {
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (26.6 + 28.57 + 2 + 15 + 12.86) > 1) {
                level = "Пороговий рівень";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (33.4 + 45.71 + 14 + 38 + 21.43) > 1) {
                level = "Галузевий стандарт";
            }
            if ((companyModel.getIndRes() * 26.6 / 1.33 + companyModel.getIndWork() * 28.57 / 1 + companyModel.getIndMar() * 2 / 0.01 + companyModel.getIndRent() * 15 / 0.15 + companyModel.getIndTime() * 12.86 / 0.09) / (43.6 + 57.86 + 38 + 51 + 38 + 57) > 1) {
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

            levelSearch.append(" Підприємство ").append(companyModel.getName()).append(" відноситься до рівня ").append(level).append(" \n");
        }
        wrapCellsAndWriteString(sheet, yOffset, yOffset + 7, companyModels.size() + 1, companyModels.size() + 6, levelSearch.toString());

        setDefaultColumnWidth(sheet, 20);
    }

    private static void createColumn(Sheet sheet, int yOffset, int colNum, Object... data) {
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.getRow(yOffset + i);
            if (row == null) {
                row = sheet.createRow(yOffset + i);
            }

            createCellWithValue(row, colNum, data[i]);
        }
    }

    private static void createRow(Sheet sheet, int rowNum, Object... data) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        for (int colNum = 0; colNum < data.length; colNum++) {
            createCellWithValue(row, colNum, data[colNum]);
        }
    }

    private static Cell createCellWithValue(Row row, int colNum, Object value) {
        Cell cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Workbook workbook = cell.getRow().getSheet().getWorkbook();

        CellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);

        if (value instanceof Number) {
            Number numberValue = (Number) value;
            cell.setCellValue(numberValue.doubleValue());
            cellStyle.setDataFormat(workbook.createDataFormat().getFormat("0,00"));
        } else {
            cell.setCellValue(String.valueOf(value));
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        cell.setCellStyle(cellStyle);
        return cell;
    }

    private static void wrapCellsAndWriteString(Sheet sheet, int rowStart, int rowEnd, int colStart, int colEnd, String message) {
        sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, colStart, colEnd));
        Row row = sheet.getRow(rowStart) == null ? sheet.createRow(rowStart) : sheet.getRow(rowStart);

        Cell cell = createCellWithValue(row, colStart, message);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_NONE);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_NONE);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_NONE);

        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
    }

    private static void setDefaultColumnWidth(Sheet sheet, int colNum) {
        for (int i = 0; i < colNum; i++) {
            sheet.setColumnWidth(i, DEFAULT_COLUMN_WIDTH);
        }
    }

    public static void exportToExcel(Collection<CompanyModel> companyModels, OutputStream out) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        generateIndicatorSheet(workbook, companyModels);
        generateMatrixSheet(workbook, companyModels);
        workbook.write(out);
    }

}
