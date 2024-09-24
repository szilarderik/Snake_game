package Jatekom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NevPanel extends JPanel{

    public NevPanel(int eaten){

        this.setBackground(Color.black);

        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        add(gameOverLabel);

        JTextField nameField;
        nameField = new JTextField(20);

        this.add(nameField);//kitöröltem a this.add(namefield)-et

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A Back gomb lenyomásakor visszalépünk a START menübe
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(NevPanel.this);
                frame.setContentPane(new StartmenuPanel());
                frame.revalidate();
            }
        });

        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A Back gomb lenyomásakor visszalépünk a START menübe
                String username=nameField.getText();
                //int eaten=getApplesEaten();
                saveNameAndNumberToFile(username, eaten);
                //***************
            }
        });

        add(backButton);
        add(okButton);

        JLabel orderLabel = new JLabel("A dicsőséglistára kerüléshez adja meg a nevét!");
        orderLabel.setForeground(Color.red);
        orderLabel.setFont(new Font("Ink Free", Font.BOLD, 20));
        add(orderLabel);
    }

    private static void saveNameAndNumberToFile(String name, int number) {

        if (!name.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("nev.txt", true))) {
                // Írjuk a bemenetről kapott adatokat a fájlhoz
                writer.write(name + " " + number);
                writer.newLine(); // Új sor karakter hozzáadása az adatok után

                System.out.println("Az adatok hozzá lettek adva a fájlhoz.");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



}
