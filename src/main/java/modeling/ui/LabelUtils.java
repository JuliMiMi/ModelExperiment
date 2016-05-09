package modeling.ui;

import javax.swing.*;

/**
 * Created by Oleksii Martyniuk on 09.05.2016.
 */
public class LabelUtils {

    public static JLabel createWrappedLabel(String text) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        label.setVerticalAlignment(SwingConstants.CENTER);

        StringBuilder wrappedText = new StringBuilder(text.length());
        wrappedText.append("<html>");
        for (char c : text.toCharArray()) {
            if (c == '\n') {
                wrappedText.append("<br>");
            } else {
                wrappedText.append(c);
            }
        }
        wrappedText.append("</html>");
        label.setText(wrappedText.toString());
        return label;
    }

}
