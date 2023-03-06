package testing;

import orafol.Display;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {
    @Test
    public void start_running_true() {
        Display dis = new Display();
        dis.run();
    }

}