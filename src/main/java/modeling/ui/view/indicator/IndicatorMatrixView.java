package modeling.ui.view.indicator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julia on 15.05.2016.
 */
public class IndicatorMatrixView {

    JPanel matrixCompet = new JPanel();
    JPanel matrixResult = new JPanel();



    Object[] header = {" ", "Indicators", "CR", "CW", "CM", "CP", "CT"};
    Object[][] etalon = {

            {"№", " ", "1", "2", "3", "4", "5"},
            {"levels of competitiveness", "rel.un", "point", "rel.un", "point","rel.un", "point","rel.un", "point","rel.un", "point" },
            {"1", "Global leadership","5", "100", "3.5", "100", "0.5", "100", "1", "100", "0.7","100"},
            {"2", "World standard", "3.81", "76.2", "2.8", "80", "0.44", "88", "0.9", "90", "0.62","88.57"},
            {"3", "National leadership", "2.94", "58.8", "2.5", "71.43", "0.36", "72", "0.78", "78", "0.46","65.71"},
            {"4", "National standard", "2.46", "49.2", "2.4", "68.57", "0.3", "60", "0.66", "66", "0.36","51.43"},
            {"5", "Sectoral leadership", "2.18", "43.6", "2.06", "58.86", "0.19", "38", "0.51", "51", "0.27","38.57"},
            {"6", "Industry standards", "1.67", "33.4", "1.6", "45.71", "0.07", "14", "0.38", "38", "0.15","21.43"},
            {"7", "Threshold level", "1.33", "26.6", "1", "28.57", "0.01", "2", "0.15", "15", "0.09","12.86" },
    };



    JTable matrixC = new JTable(etalon, header);
    matrixCompet.add(matrixC);



    }
}
