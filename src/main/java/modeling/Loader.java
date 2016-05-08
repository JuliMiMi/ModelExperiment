package modeling;

import modeling.ui.GreetingsView;
import modeling.ui.ViewHolder;
import modeling.ui.ViewManager;

import javax.swing.*;

/**
 * Created by Julia on 07.05.2016.
 */
public class Loader {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewHolder viewHolder = ViewManager.getViewHolder();
            GreetingsView greetingsView = ViewManager.getGreetingsView();

            viewHolder.setView(greetingsView);
        });
    }
}
