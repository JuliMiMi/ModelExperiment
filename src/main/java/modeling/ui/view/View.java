package modeling.ui.view;

import javax.swing.*;

/**
 * Created by Oleksii Martyniuk on 14.05.2016.
 */
public interface View {

    JPanel getContent();

    default JMenuBar getMenu() {
        return new JMenuBar();
    }

}
