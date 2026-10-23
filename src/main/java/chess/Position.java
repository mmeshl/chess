package chess;

import java.util.ArrayList;
import java.util.List;

public record Position(Integer col, Integer row) {
    public static List<List<Position>> range(Position start, Position end) {
        return range(start.col, start.row, end.col, end.row);
    }

    public Position(Character col, Integer row) {
        this(col - 'A', row - 1);
    }

    public Position offset(Integer columnOffset, Integer rowOffset) {
        return new Position(col + columnOffset, row + rowOffset);
    }

    public Position oppositeRow(Integer columnOffset, Integer opposingEdgeRow) {
        return new Position(col + columnOffset, opposingEdgeRow - row - 1);
    }

    public Position oppositeRow(Integer opposingEdgeRow) {
        return oppositeRow(0, opposingEdgeRow);
    }

    private static List<List<Position>> range(Integer startX, Integer startY, Integer endX, Integer endY) {
        List<List<Position>> positions = new ArrayList<>();

        for (int y = startY; y <= endY; y++) {
            positions.add(new ArrayList<>());
            final List<Position> row = positions.getLast();

            for (int x = startX; x <= endX; x++) {
                row.add(new Position(x, y));
            }
        }

        return positions;
    }
}
