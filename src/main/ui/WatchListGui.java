package ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Show;
import model.WatchList;

public class WatchListGui extends JFrame {

    private WatchList watchlist;
    private JTextField inputName;
    private JTextField inputEps;
    private JButton confirmName;
    private JButton confirmEps;
    private Show show;

    public WatchListGui() {
        super("WatchList GUI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        watchlist = new WatchList();

        inputName = new JTextField();
        inputEps = new JTextField();

        buttonPanel();

        setVisible(true);

    }

    // EFFECTS: creates control buttons
    private void buttonPanel() {
        inputName = new JTextField("Add Show Name");
        inputEps = new JTextField("Add Episodes");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(inputName);
        buttonPanel.add(inputEps);
        buttonPanel.add(new JButton(new AddShowAction()));

        add(buttonPanel, BorderLayout.NORTH);


    }

    //MODIFIES: this
    //EFFECTS: action that adds a show to watchlist
    private class AddShowAction extends AbstractAction {

        AddShowAction() {
            super("Add Show");
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = inputName.getText();
            int eps = Integer.parseInt(inputEps.getText());

            if (!name.equals("") && eps > 0) {
                show = new Show(name, eps);
            }

            if (!watchlist.isContained(show)) {
                watchlist.addShow(show);
            }
        }
    }
}
