package com.accenture.monitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

/**
 * Created by oliver.g.slade & niall.h.marshall on 24/08/2016.
 */
public class Monitor {
    private JPanel mainPanel;
    private JButton startMonitor;
    private JButton writeTo;
    private JTextArea outputArea;
    private JLabel title;
    private JScrollPane scrollPanel;


    public Monitor() {
        if (outputArea.getText().equals("")){
            writeTo.setEnabled(false);
        } else {
            writeTo.setEnabled(true);
        }
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

                writeTo.setEnabled(true);
            }
        });

        writeTo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                super.mouseClicked(mouseEvent);

                System.out.println("Working");

                String outputText = outputArea.getText();

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
                Date date = new Date();

                String dateString = dateFormat.format(date);
                String username = System.getProperty("user.name");
                new File("C://Users//" + username + "//Documents//WifiTesting").mkdirs();
                File csvfile = new File("C://Users//" + username + "//Documents//WifiTesting//wifi-report-" + dateString + ".csv");

                if (!csvfile.exists()) {
                    try {
                        csvfile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                FileWriter fw = null;
                try {
                    fw = new FileWriter(csvfile.getAbsoluteFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedWriter writer = null;
                    writer = new BufferedWriter(fw);
                try {
                    Scanner inputStream = new Scanner(outputText);

                    String[] lines = outputText.split("\n");

                    for (int i = 0; i < lines.length ; i++) {

                        String line = lines[i];
                        String[] newLines = line.split(":");

                        //if (!newLines[i].isEmpty()){
                        for (int e = 0; e < lines.length; e++) {
                            writer.write(lines[e] + ",\n");
                            System.out.println(newLines[e]);
                            //}
                        }
                    }

                    inputStream.close();
                    writer.close();

                } catch (IOException d) {


                }
            }
        });

    }
//    public static void Writer() {
//
//        if (outputArea.getText().equals("")){
//            writeTo.setEnabled(false);
//        } else {
//            writeTo.setEnabled(true);
//        }
//
//        writeTo.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent mouseEvent) {
//
//                super.mouseClicked(mouseEvent);
//                System.out.println("Working");
//
//                String outputText = outputArea.getText();
//
//                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                Date date = new Date();
//
//                String dateString = dateFormat.format(date);
//                String username = System.getProperty("user.name");
//                String csvfile = username + "Documents//Wifi-Monitor//wifi-report-" + dateString;
//                BufferedWriter writer = null;
//
//                //"C://" + username + "Documents//Wifi-Monitor//" +
//                try {
//                    writer = new BufferedWriter(new FileWriter(csvfile));
//                } catch (IOException e){
//                    System.out.println(e);
//                }
//                try {
//                    Scanner inputStream = new Scanner(outputText);
//                    while (inputStream.hasNextLine()) {
//                        CSVUtils.writeLine(writer, Arrays.asList(outputText));
//                    }
//                    writer.close();
//                    inputStream.close();
//                } catch (IOException d) {
//
//                }
//            }
//        });
//    }

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