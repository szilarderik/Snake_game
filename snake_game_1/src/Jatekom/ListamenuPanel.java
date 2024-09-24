package Jatekom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class ListamenuPanel extends JPanel {

    public ListamenuPanel(){

        JButton backButton = new JButton("Back");
        JTextArea nevsorTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(nevsorTextArea);

        nevsorTextArea.setRows(10);
        nevsorTextArea.setColumns(20);

        nevsorTextArea.setEditable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A Back gomb lenyomásakor visszalépünk a START menübe
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ListamenuPanel.this);
                frame.setContentPane(new StartmenuPanel());
                frame.revalidate();
            }
        });
        try {
            String eleresiUt = "nev.txt";
            Map<String, Integer> nevekMap = beolvasNeveket(eleresiUt);
            megjelenitNevsor(nevsorTextArea, nevekMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(backButton);
        add(scrollPane);

    }

    public static Map<String, Integer> beolvasNeveket(String eleresiUt) throws IOException {
        Map<String, Integer> nevekMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(eleresiUt))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                String[] adatok = sor.split("\\s+");
                if (adatok.length >= 2) {
                    String nev = adatok[0];
                    int egeszSzam = Integer.parseInt(adatok[1]);
                    nevekMap.put(nev, egeszSzam);
                }
            }
        }

        return nevekMap;
    }

    public void megjelenitNevsor(JTextArea nevsorTextArea, Map<String, Integer> nevekMap) {
        nevekMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> nevsorTextArea.append(entry.getKey() + ": " + entry.getValue() + "\n"));
    }
}
