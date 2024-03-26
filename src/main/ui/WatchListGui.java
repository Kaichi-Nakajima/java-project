package ui;

//code Reference: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
//                https://docs.oracle.com/javase/jp/8/docs/api/javax/swing/JList.html
//                https://stackoverflow.com/questions/6420623/how-to-bind-arraylist-to-jlist

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Show;
import model.WatchList;
import persistence.JsonReader;
import persistence.JsonWriter;

public class WatchListGui extends JFrame {

    private static final String JSON_STORE = "./data/watchlist.json";
    private WatchList watchlist;
    private JTextField inputName;
    private JTextField inputEps;
    private JList<Object> shows;
    private Show show;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public WatchListGui() {
        super("WatchList GUI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        watchlist = new WatchList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        inputName = new JTextField();
        inputEps = new JTextField();

        buttonPanel();
        actionPanel();

        setVisible(true);

    }

    // EFFECTS: creates panel with control buttons and user input text field
    private void buttonPanel() {
        inputName = new JTextField("Add Show Name");
        inputEps = new JTextField("Add Episodes");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(inputName);
        buttonPanel.add(inputEps);
        buttonPanel.add(new JButton(new AddShowAction()));
        buttonPanel.add(new JButton(new RemoveAction()));

        add(buttonPanel, BorderLayout.NORTH);


    }

    //EFFECTS: creates panel with control buttons
    private void actionPanel() {

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(4,1));
        actionPanel.add(new JButton(new SaveAction()));
        actionPanel.add(new JButton(new LoadAction()));
        actionPanel.add(new JButton(new ViewAction()));

        add(actionPanel, BorderLayout.SOUTH);
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

    private class RemoveAction extends AbstractAction {

        RemoveAction() {
            super("Remove Show");
        }

        /**
         * Invoked when an action occurs.
         *
         * @param ev the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent ev) {

            String nameShow = inputName.getText();

            show = watchlist.getShowName(nameShow);
            watchlist.removeShow(show);

        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(watchlist);
                jsonWriter.close();
                System.out.println("Saved WatchList to " + JSON_STORE);
            } catch (FileNotFoundException et) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }

        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        /**
         * Invoked when an action occurs.
         *
         * @param evt the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                watchlist = jsonReader.read();
                System.out.println("Loaded WatchList from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to reader from file: " + JSON_STORE);
            }

        }
    }

    private class ViewAction extends AbstractAction {

        ViewAction() {
            super("View List");
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            shows = new JList<>(watchlist.getShowNamesList().toArray());
            add(shows, BorderLayout.CENTER);
            setVisible(true);
        }
    }


}
