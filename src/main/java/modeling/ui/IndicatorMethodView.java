package modeling.ui;

import sun.awt.DisplayChangedListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.ChangedCharSetException;
import java.awt.*;

/**
 * Created by Julia on 09.05.2016.
 */
public class IndicatorMethodView extends JPanel {

    private Dimension VIEW_SIZE = new Dimension(400, 200);

    IndicatorMethodView() {
        super();
        build();
    }

    private void build() {
        this.setLayout(new FlowLayout());
        setSize(VIEW_SIZE);
        setOpaque(true);
        add(buildAddCompanyCheck());
        add(buildLabelSelectNum());
        add(buildCounterCompetitors());
        add(buildGroup());
    }

    private void add(GridBagLayout initialData) {
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel jlb = new JLabel("Test");
        initialData.addLayoutComponent(jlb,gbc );

    }

    private JCheckBox buildAddCompanyCheck() {
        JCheckBox addCompanyCheck = new JCheckBox("Compare several companies");
        return addCompanyCheck;
    }

    private JLabel buildLabelSelectNum() {
        JLabel selectNum = new JLabel("Number of competitors");
        return selectNum;
    }

    private JSpinner buildCounterCompetitors() {
        SpinnerNumberModel spm = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner counterCompetitors = new JSpinner(spm);
        counterCompetitors.setPreferredSize(new Dimension(40, 20));
        return counterCompetitors;
    }

        private GridBagLayout buildGroup() {
            GridBagLayout initialData = new GridBagLayout();
            GridBagConstraints gbc = new GridBagConstraints();
            JLabel jlb = new JLabel("Test");
        initialData.addLayoutComponent(jlb,gbc );
        return initialData;


    }
}
