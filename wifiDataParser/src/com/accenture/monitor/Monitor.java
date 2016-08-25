package com.accenture.monitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by oliver.g.slade on 24/08/2016.
 */
public class Monitor {
    private JPanel mainPanel;
    private JButton startMonitor;
    private JTextArea outputArea;
    private JLabel title;
    private JScrollPane scrollPanel;

    public Monitor() {
        startMonitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                Runtime runtime = Runtime.getRuntime();
                try {
                    Process proc = runtime.exec("netsh wlan show networks mode=bssid");
                    BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line;
                    while ((line = input.readLine()) != null) {
                        outputArea.append(line + "\n");
                    }
                    input.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("DDCN Wifi Monitor");
            frame.setPreferredSize(new Dimension(640, 480));
            frame.setContentPane(new Monitor().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        });
    }
}