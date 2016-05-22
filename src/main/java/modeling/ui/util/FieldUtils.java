package modeling.ui.util;

import org.apache.commons.lang3.StringUtils;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.util.function.Consumer;

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

    public static void bindStringField(JTextField field, Consumer<String> setter, Consumer<Boolean> validSetter) {
        onDocumentChange(field, e -> {
            String text = field.getText();
            if (StringUtils.isNotEmpty(text)) {
                setter.accept(text);
                validSetter.accept(Boolean.TRUE);
            } else {
                validSetter.accept(Boolean.FALSE);
            }
        });
    }

    public static void bindDoubleField(JTextField field, Consumer<Double> setter, Consumer<Boolean> validSetter) {
        onDocumentChange(field, e -> {
            boolean valid = validateDoubleFields(field);
            validSetter.accept(valid);
            if (valid) {
                setter.accept(parseDoubleField(field));
            }
        });
    }

    public static void clearFields(JTextComponent... fields) {
        for (JTextComponent field : fields) {
            field.setText(null);
            field.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public static double parseDoubleField(JTextField textField) {
        return Double.parseDouble(textField.getText());
    }

    private static void onDocumentChange(JTextComponent component, Consumer<DocumentEvent> listener) {
        component.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.accept(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.accept(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.accept(e);
            }
        });
    }
}
