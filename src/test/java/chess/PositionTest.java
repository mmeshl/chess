package chess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public final class PositionTest {
    @Test
    public void testConstructor() {
        Position position = new Position('A', 1);
        assertEquals(0, position.col());
        assertEquals(0, position.row());

        position = new Position('H', 8);
        assertEquals(7, position.col());
        assertEquals(7, position.row());
    }

    @Test
    public void testOffset() {
        Position position = new Position('A', 1);
        assertEquals(1, position.offset(1, 0).col());
        assertEquals(1, position.offset(0, 1).row());

        position = new Position('H', 8);
        assertEquals(6, position.offset(-1, 0).col());
        assertEquals(6, position.offset(0, -1).row());
        assertEquals(new Position('G', 7), position.offset(-1, -1));
    }

    @Test
    public void testOppositeRow() {
        Position position = new Position('A', 1);
        assertEquals(new Position('A', 8), position.oppositeRow(8));
        assertEquals(new Position('B', 8), position.oppositeRow(1, 8));

        position = new Position('A', 2);
        assertEquals(new Position('A', 7), position.oppositeRow(8));
    }
}
