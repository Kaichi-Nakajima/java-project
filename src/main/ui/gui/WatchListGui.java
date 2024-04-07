package ui.gui;

//code Reference: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
//                https://docs.oracle.com/javase/jp/8/docs/api/javax/swing/JList.html
//                https://stackoverflow.com/questions/6420623/how-to-bind-arraylist-to-jlist
//                https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
//                https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


import model.Event;
import model.EventLog;
import model.Show;
import model.WatchList;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents applications main window frame
public class WatchListGui extends JFrame {

    private static final String JSON_STORE = "./data/watchlist.json";
    private WatchList watchlist;
    private JTextField inputName;
    private JTextField inputEps;
    private JList<Object> shows;
    private Show show;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private WatchListImage image;

    //EFFECTS: runs Watch List GUI
    public WatchListGui() throws FileNotFoundException {
        super("WatchList GUI");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        watchlist = new WatchList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        inputName = new JTextField();
        inputEps = new JTextField();

        buttonPanel();
        actionPanel();
        addImageGUI();

        pack();
        setVisible(true);

        closeGui();

    }

    // EFFECTS: creates panel with control buttons and user input text field
    private void buttonPanel() {
        inputName = new JTextField("Add Show Name");
        inputEps = new JTextField("Add Episodes");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(inputName);
        buttonPanel.add(inputEps);
        buttonPanel.add(new JButton(new AddShowAction()));
        buttonPanel.add(new JButton(new RemoveAction()));

        add(buttonPanel, BorderLayout.WEST);


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

    //MODIFIES: show, watchlist
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

    //MODIFIES: show, watchlist
    //EFFECTS: action that removes a show from watchlist
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

    //MODIFIES: this
    //EFFECTS: action that saves watchlist to file
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

    //MODIFIES: this
    //EFFECTS: action that loads watchlist from file
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

    //MODIFIES: this
    //EFFECTS: action that displays names of shows in the watchlist
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

    //MODIFIES: image
    //EFFECTS: adds image to gui
    private void addImageGUI() {
        image = new WatchListImage(this);
        add(image, BorderLayout.NORTH);
    }

    //
    public void closeGui() {
        EventLog eventLog = EventLog.getInstance();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                for (Event event: eventLog) {
                    System.out.println(event);
                }
                System.exit(0);
            }
        });
    }


}
