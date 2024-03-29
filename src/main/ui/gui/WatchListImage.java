package ui.gui;

import javax.swing.*;

// Code Reference: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
// Image Reference: https://openart.ai/discovery/sd-1008661589823598612

public class WatchListImage extends JPanel {
    private WatchListGui watchListGui;
    private JPanel imagePanel;
    private ImageIcon logo;
    private JLabel imageAsLabel;

    public WatchListImage(WatchListGui watchListGui) {
        this.watchListGui = watchListGui;

        imagePanel = new JPanel();

        loadImage();
        setImage();

        add(imagePanel);


    }

    //EFFECTS: loads the image
    public void loadImage() {
        String sep = System.getProperty("file.separator");
        logo = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "MYSHOWWATCHLIST.png");
    }

    //EFFECTS: sets the image to panel
    public void setImage() {
        imageAsLabel = new JLabel(logo);
        imagePanel.add(imageAsLabel);
    }
}
