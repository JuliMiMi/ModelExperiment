package modeling;

import modeling.ui.view.GreetingsView;
import modeling.ui.view.indicator.IndicatorMethodView;
import modeling.ui.ViewHolder;
import modeling.ui.view.ViewManager;

import javax.swing.*;

/**
 * Created by Julia on 07.05.2016.
 */
public class Loader {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewHolder viewHolder = ViewManager.getViewHolder();
            GreetingsView greetingsView = ViewManager.getGreetingsView();
            IndicatorMethodView viewMethod1 = ViewManager.getIndicatorMethodView();

            viewHolder.setView(greetingsView);
            viewHolder.setView(viewMethod1);
        });
    }
}
