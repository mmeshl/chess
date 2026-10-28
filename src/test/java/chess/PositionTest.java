package chess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public final class PositionTest {
    private static final PositionFactory positionFactory = new PositionFactory();

    @Test
    public void testConstructor() {
        Position position = positionFactory.create('A', 1);
        assertEquals(0, position.col());
        assertEquals(0, position.row());

        position = positionFactory.create('H', 8);
        assertEquals(7, position.col());
        assertEquals(7, position.row());
    }

    @Test
    public void testOffset() {
        Position position = positionFactory.create('A', 1);
        assertEquals(1, position.offset(1, 0).col());
        assertEquals(1, position.offset(0, 1).row());

        position = positionFactory.create('H', 8);
        assertEquals(6, position.offset(-1, 0).col());
        assertEquals(6, position.offset(0, -1).row());
        assertEquals(positionFactory.create('G', 7), position.offset(-1, -1));
    }

    @Test
    public void testOppositeRow() {
        Position position = positionFactory.create('A', 1);
        assertEquals(positionFactory.create('A', 8), position.oppositeRow(8));
        assertEquals(positionFactory.create('B', 8), position.oppositeRow(1, 8));

        position = positionFactory.create('A', 2);
        assertEquals(positionFactory.create('A', 7), position.oppositeRow(8));
    }
}
