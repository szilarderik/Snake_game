package Jatekom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FomenuPanel extends JPanel {

    public FomenuPanel(){

        JButton goButton = new JButton("GO");

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A GO gomb lenyomásakor indul a Snake játék (itt még nincs megvalósítva)
                JOptionPane.showMessageDialog(FomenuPanel.this, "Snake játék indul!");
                //************
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(FomenuPanel.this);
                frame.setContentPane(new GamePanel());
                frame.revalidate();
                //*************** ez csak proba

            }
        });

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // A Back gomb lenyomásakor visszalépünk a START menübe
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(FomenuPanel.this);
                frame.setContentPane(new StartmenuPanel());
                frame.revalidate();
            }
        });

        add(goButton);
        add(backButton);
    }
}
