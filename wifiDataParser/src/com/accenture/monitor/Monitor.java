package com.accenture.monitor;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliver.g.slade on 24/08/2016.
 */
public class Monitor {
    private JPanel mainPanel;
    private JButton startMonitor;
    private JTextArea outputArea;
    private JScrollBar scrollBar;
    private JLabel title;

    public Monitor() {
        startMonitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                String user = new String(System.getProperty("user.name"));
                StringBuffer dataBuffer = new StringBuffer();
                List<String> newList = new ArrayList<>();
                try {
                    newList = FileUtils.readLines(new File("C:\\Users\\" + user + "\\Documents\\DDCN-Wi-Fi-Testing\\wifi-test.txt"), "UTF-16");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (String line : newList) {
                    String lineString = line.toString();
                    String newString = lineString.trim().replaceAll(" ", "").replaceAll("\\t", "");
                    System.out.println(newString);

                    outputArea.append(newString + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Monitor");
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setContentPane(new Monitor().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
