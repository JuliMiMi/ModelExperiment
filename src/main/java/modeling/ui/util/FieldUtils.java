package modeling.ui.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Created by Oleksii Martyniuk on 14.05.2016.
 */
public class FieldUtils {

    public static boolean validateDoubleFields(JTextField... fields) {
        boolean valid = true;
        for (JTextField field : fields) {
            try {
                field.setBorder(BorderFactory.createCompoundBorder());
                String text = field.getText();
                Double.parseDouble(text);
            } catch (NullPointerException | NumberFormatException e) {
                valid = false;
                field.setBorder(new LineBorder(Color.RED));
            }
        }

        return valid;
    }

    public static void clearFields(JTextComponent... fields){
        for (JTextComponent field : fields) {
            field.setText(null);
            field.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public static double parseDoubleField(JTextField textField) {
        return Double.parseDouble(textField.getText());
    }
}
