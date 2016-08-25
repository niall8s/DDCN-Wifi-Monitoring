package com.accenture.monitor;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
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
    private JLabel title;
    private JScrollPane scrollPanel;

    public Monitor() {
        startMonitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                String user = new String(System.getProperty("user.name"));

                Runtime runtime = Runtime.getRuntime();
                try {
                    Process proc = runtime.exec("netsh wlan show networks mode=bssid");
                    BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    System.out.println(input.readLine());
                    input.close();
//                    while (line != null) {
//                        if(line.contains("BPV")) {
//                            System.out.println(line);
//                        }
//                    }
                } catch (IOException e) {
                    System.out.println(e);
                }

                StringBuffer dataBuffer = new StringBuffer();
                List<String> newList = new ArrayList<>();
                try {
                    newList = FileUtils.readLines(new File("C:\\Users\\" + user + "\\Documents\\DDCN-Wi-Fi-Testing\\wifi-test.txt"), "UTF-16");
                } catch (IOException e) {
                    System.out.println(e);
                }

                for (String line : newList) {
                    String lineString = line.toString();
                    String newString = lineString.trim().replaceAll(" ", "").replaceAll("\\t", "");
                    //System.out.println(newString);

                    if (newString.contains("Otherrates")) {
                        newString = newString + "\n";
                    }

                    if (newString.contains("Encryption")) {
                        newString = newString + "\n";
                    }

                    outputArea.append(newString + "\n");
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