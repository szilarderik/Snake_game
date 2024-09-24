package Jatekom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH=600;
    static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;
    static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT/UNIT_SIZE);
    //************
    int DELAY=140;
    //**********
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];

    int bodyParts=6;

    int eatenAll;
    int applesEaten;
    int appleX;
    int appleY;

    //******
    int plumsEaten;
    int plumX;
    int plumY;

    char direction='R';
    boolean running=false;
    Timer timer;
    Random random;

    Random random1;



    GamePanel(){
        random=new Random();
        random1=new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){
        newApple();
        //**************
        newPlum();;
        //***********
        running=true;
        timer=new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if(running) {
			//*************

            //g.setColor(Color.white);
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}

            g.setColor(Color.yellow);

            try (BufferedReader reader = new BufferedReader(new FileReader("pipak.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] coordinates = line.split(" ");
                    if (coordinates.length == 2) {
                        int row = Integer.parseInt(coordinates[0]);
                        int col = Integer.parseInt(coordinates[1]);
                        g.fillRect(col*UNIT_SIZE, row*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    } else {
                        System.err.println("Hibás formátum a fájlban: " + line);
                    }
                }
                //System.out.println("A pipák helyzete be lett olvasva a fájlból.");
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
            }
			//****************
            g.setColor(Color.blue);
            g.fillOval(plumX, plumY, UNIT_SIZE, UNIT_SIZE);
            //*************
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(45,180,0));
                    //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());

            g.drawString("Score: "+eatenAll, (SCREEN_WIDTH - metrics.stringWidth("Score: "+eatenAll))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }

    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    //****************
    public void newPlum() {
        plumX = random1.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        plumY = random1.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        // A szilva helyét ellenőrizni kell, hogy ne essen egybe az alma helyével
        while (plumX == appleX && plumY == appleY) {
            plumX = random1.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            plumY = random1.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }
    }
    //*******************
    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            eatenAll++;
            newApple();
        }
    }
    //*****************
    public void checkPlum(){
        if((x[0] == plumX) && (y[0] == plumY)) {
            bodyParts++;
            //++++++++++++++
            int sebesseg=DELAY-1;
            timer=new Timer(sebesseg,this);
            timer.start();
            //+++++++++++++++
            eatenAll+=5;
            plumsEaten++;
            newPlum();

        }
    }


    //*******************
    public void checkCollisions(){
        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                running = false;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("pipak.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.split(" ");
                if (coordinates.length == 2) {
                    int row = Integer.parseInt(coordinates[0]);
                    int col = Integer.parseInt(coordinates[1]);
                    if(x[0]==col*UNIT_SIZE && y[0]==row*UNIT_SIZE){
                        running=false;
                    }
                } else {
                    System.err.println("Hibás formátum a fájlban: " + line);
                }
            }

        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }

        //check if head touches left border
        if(x[0] < 0) {
            running = false;
        }
        //check if head touches right border
        if(x[0] > SCREEN_WIDTH-25) {
            running = false;
        }
        //check if head touches top border
        if(y[0] < 0) {
            running = false;
        }
        //check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT-25) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }

    }
    public void gameOver(Graphics g){
        //***************
        String filePath = "pipak.txt";

        try {
            File file = new File(filePath);

            // Ellenőrizzük, hogy a fájl létezik-e
            if (file.exists()) {
                // Töröljük a fájl tartalmát
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("");
                fileWriter.close();

                //System.out.println("A fájl tartalma törölve.");
            } else {
                System.out.println("A fájl nem található.");
            }
        } catch (IOException e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }


        //**************
        //
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        //Game Over text

        /*g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);*/
        //**************
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
        frame.setContentPane(new NevPanel(this.getApplesEaten()));
        frame.revalidate();
        //*****************
        //*******************
        //File fileToDelete = new File("pipak.txt");
        //*****************
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkApple();
            //******************
            checkPlum();
            //***************
            checkCollisions();
        }
        repaint();
    }


    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    public int getApplesEaten(){
        if(running == false){
            return applesEaten+plumsEaten*5;
        }
        return applesEaten+plumsEaten*2;
    }

    public int getPlumsEaten(){
        if(running == false){
            return plumsEaten;
        }
        return plumsEaten;
    }

    public boolean isRunning(){
        return running;
    }

    public Timer getTimer(){
        return timer;
    }

    public int[] getfejX() {
        return Arrays.copyOf(x, x.length);
    }

    public int[] getfejY(){
        return Arrays.copyOf(y, y.length);
    }

    public int getAppleX(){
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public int getPlumX(){
        return plumX;
    }

    public int getPlumY(){
        return plumY;
    }

}
