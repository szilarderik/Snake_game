package Jatekom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TervezomenuPanel extends JPanel {

    private final int MATRIX_SIZE = 24;
    private JToggleButton[][] matrixButtons;

    public boolean adtakemeg=false;

    public TervezomenuPanel(){


        // Mátrix létrehozása
        matrixButtons = new JToggleButton[MATRIX_SIZE][MATRIX_SIZE];
        JPanel matrixPanel = new JPanel(new GridLayout(MATRIX_SIZE, MATRIX_SIZE));

        int squareSize = 22;
        int panelSize = squareSize * MATRIX_SIZE;
        matrixPanel.setPreferredSize(new Dimension(panelSize, panelSize));

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrixButtons[i][j] = new JToggleButton();
                matrixPanel.add(matrixButtons[i][j]);
            }
        }


        JButton backButton = new JButton("BACK");
        JButton saveButton = new JButton("SAVE");


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A Back gomb lenyomásakor visszalépünk a START menübe
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TervezomenuPanel.this);
                frame.setContentPane(new StartmenuPanel());
                frame.revalidate();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A Back gomb lenyomásakor visszalépünk a START menübe
                savePipakToFile();
                adtakemeg=true;
            }
        });
        add(backButton,BorderLayout.NORTH);
        add(saveButton,BorderLayout.NORTH);
        add(matrixPanel, BorderLayout.CENTER);

        setVisible(true);
    }


    private void savePipakToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pipak.txt"))) {
            // Pipák helyzetének elmentése fájlba
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    if (matrixButtons[i][j].isSelected()) {
                        writer.write(i + " " + j);
                        writer.newLine();
                    }
                }
            }
            System.out.println("A pipák helyzete el lett mentve a fájlba.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean getAdtakmege(){
        return adtakemeg;
    }
}
