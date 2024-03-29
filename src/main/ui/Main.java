package ui;

import ui.gui.WatchListGui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main extends JFrame {
    public static void main(String[] args) {
        try {
            new WatchListGui();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found ");
        }
    }
}
