package Jatekom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class StartmenuPanel extends JPanel {

    public StartmenuPanel(){

        setPreferredSize(new Dimension(600, 600)); // Beállítjuk a méretet

        JButton startButton = new JButton("START");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A START gomb lenyomásakor váltunk az új menüre
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartmenuPanel.this);
                frame.setContentPane(new FomenuPanel());
                frame.revalidate();
            }
        });

        JButton listazButton = new JButton("DICSOSEGLISTA");

        listazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A START gomb lenyomásakor váltunk az új menüre
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartmenuPanel.this);
                frame.setContentPane(new ListamenuPanel());
                frame.revalidate();
            }
        });

        JButton tervezoButton = new JButton("PALYATERVEZO");

        tervezoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A START gomb lenyomásakor váltunk az új menüre
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartmenuPanel.this);
                frame.setContentPane(new TervezomenuPanel());
                frame.revalidate();
            }
        });

        add(startButton);
        add(listazButton);
        add(tervezoButton);
    }
}
