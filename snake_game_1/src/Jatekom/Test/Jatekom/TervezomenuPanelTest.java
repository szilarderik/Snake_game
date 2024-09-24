package Jatekom;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TervezomenuPanelTest {

    private TervezomenuPanel tervezomenuPanel;

    @Before
    public void setUp() {
        tervezomenuPanel = new TervezomenuPanel();
    }

    @Test
    public void getAdtakmege() {
        assertFalse(tervezomenuPanel.getAdtakmege());
    }
}