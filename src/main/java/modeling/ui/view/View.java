package modeling.ui.view;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * Created by Oleksii Martyniuk on 14.05.2016.
 */
public interface View {

    JPanel getContent();

    default JMenuBar getMenu() {
        return new JMenuBar();
    }

}
