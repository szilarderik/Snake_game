package Jatekom;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.Map;

import static org.junit.Assert.*;


public class ListamenuPanelTest {

    private ListamenuPanel listamenuPanel;

    @Before
    public void setUp() {
        listamenuPanel = new ListamenuPanel();
    }


    @Test
    public void megjelenitNevsor() {
        JTextArea nevsorTextArea = new JTextArea();
        Map<String, Integer> nevekMap = Map.of("John", 10, "Alice", 5, "Bob", 8);

        listamenuPanel.megjelenitNevsor(nevsorTextArea, nevekMap);

        // Az ellenőrzés helyes, ha a szöveg az elvárt sorrendben jelenik meg
        String expectedText = "John: 10\nBob: 8\nAlice: 5\n";
        assertEquals(expectedText, nevsorTextArea.getText());
    }

}