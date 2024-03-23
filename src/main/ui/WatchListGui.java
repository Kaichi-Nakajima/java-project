package ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Show;
import model.WatchList;

public class WatchListGui extends JFrame{

    private WatchList watchlist;
    private JTextField inputName;
    private JTextField inputEps;
    private JButton confirm;

    public WatchListGui() {
        super("WatchList GUI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        watchlist = new WatchList();

        inputName = new JTextField();
        inputEps = new JTextField();
        confirm = new JButton("confirm");

    }

    // EFFECTS: creates control buttons
    private void buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8,1));
        buttonPanel.add(new JButton(new AddCodeAction()));


    }

    //MODIFIES: this
    //EFFECTS: adds a show to watchlist
    private void doAddShow() {
        Show show;
        System.out.println("enter the name of the show");
        String name = input.next();
        System.out.println("enter number of episodes");
        int episodes = input.nextInt();
        show = new Show(name, episodes);

        if (!watchlist.isContained(show)) {
            watchlist.addShow(show);
        } else {
            System.out.println("show is already in watch list");
        }
    }

    //EFFECTS: prompts user to select a show
    private Show selectShow() {
        String selection = "";

        if (watchlist.isEmpty()) {
            System.out.println("there is nothing in the watchlist");
            System.out.println("add a show to the watchlist");
            return null;
        }

        while (selection.equals("")) {
            System.out.println("type name of the show");
            selection = input.next();
        }


        return watchlist.getShowName(selection);
    }
}
