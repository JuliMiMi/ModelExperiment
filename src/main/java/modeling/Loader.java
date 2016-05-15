package modeling;

import modeling.ui.ViewHolder;
import modeling.ui.view.ViewManager;
import modeling.ui.view.indicator.IndicatorMethodView;

import javax.swing.SwingUtilities;

/**
 * Created by Julia on 07.05.2016.
 */
public class Loader {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewHolder viewHolder = ViewManager.getViewHolder();
            IndicatorMethodView viewMethod1 = ViewManager.getIndicatorMethodView();
            viewHolder.setView(viewMethod1);
        });
    }
}
