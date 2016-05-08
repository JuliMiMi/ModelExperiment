package modeling.ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksii Martyniuk on 08.05.2016.
 */
public class ViewManager {

    private static Map<Class, Object> ui = new HashMap<>();

    public static ViewHolder getViewHolder() {
        return lazyInit(ViewHolder.class);
    }

    public static GreetingsView getGreetingsView() {
        return lazyInit(GreetingsView.class);
    }

    @SuppressWarnings("unchecked")
    private static <O> O lazyInit(Class<O> objectClass) {
        Object o = ui.get(objectClass);

        if (o == null) {
            try {
                o = objectClass.newInstance();
                ui.put(objectClass, o);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return (O) o;
    }

}
