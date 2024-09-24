package Jatekom;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class GamePanelTest {

    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
    }

    @Test
    public void startGame() {
        gamePanel.startGame();
        assertTrue(gamePanel.isRunning());
        assertNotNull(gamePanel.getTimer());
    }

    @Test
    public void paintComponent() {
        //gamePanel.paintComponent(gamePanel.getGraphics());
    }

    @Test
    public void draw() {
       // gamePanel.paintComponent(gamePanel.getGraphics());
    }

    @Test
    public void newApple() {
        gamePanel.newApple();
        assertNotEquals(gamePanel.getAppleX(), gamePanel.getPlumX());
        assertNotEquals(gamePanel.getAppleY(), gamePanel.getPlumY());
    }

    @Test
    public void newPlum() {
        gamePanel.newPlum();
        assertNotEquals(gamePanel.getPlumX(), gamePanel.getAppleX());
        assertNotEquals(gamePanel.getPlumY(), gamePanel.getAppleY());
    }

    @Test
    public void move() {
        int[] initialX = gamePanel.getfejX().clone();
        int[] initialY = gamePanel.getfejY().clone();
        gamePanel.move();
        assertNotEquals(initialX[0], gamePanel.getfejX());
        assertNotEquals(initialY[0], gamePanel.getfejY());
    }

    @Test
    public void checkApple() {
        int initialApplesEaten = gamePanel.getApplesEaten();
        gamePanel.checkApple();
        assertEquals(initialApplesEaten + gamePanel.getPlumsEaten()*5, gamePanel.getApplesEaten());
    }

    @Test
    public void checkPlum() {
        int initialPlumsEaten = gamePanel.getPlumsEaten();
        gamePanel.checkPlum();
        assertEquals(initialPlumsEaten, gamePanel.getPlumsEaten());
    }

    @Test
    public void checkCollisions() {
        gamePanel.checkCollisions();
        assertFalse(gamePanel.isRunning());
    }

    @Test
    public void gameOver() {
        assertTrue(gamePanel.isRunning());
    }


}