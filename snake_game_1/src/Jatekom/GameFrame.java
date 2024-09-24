package Jatekom;

import javax.swing.*;

public class GameFrame extends JFrame{

    GameFrame(){

        //this.add(new GamePanel());
        this.add(new StartmenuPanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}
