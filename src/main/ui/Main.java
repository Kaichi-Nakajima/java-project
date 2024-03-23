package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Main extends JFrame {
    public static void main(String[] args) {
        try {
            new WatchListTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found ");
        }
    }
}
