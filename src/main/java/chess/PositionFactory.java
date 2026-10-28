package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class PositionFactory {
    public Position create(Character col, Integer row) {
        return new Position(col - 'A', row - 1);
    }

    public Position create(Integer col, Integer row) {
        return create(intToChar(col), row);
    }

    public List<Position> createHorizontalRange(Integer row, Character startCol, Character endCol) {
        return IntStream.rangeClosed(startCol, endCol)
                .mapToObj(col -> create(col, row))
                .toList();
    }

    public List<Position> createMultipleOnSameRow(Integer row, Character... columns) {
        return createMultipleOnSameRow(row, Arrays.asList(columns));
    }

    public List<Position> createMultipleOnSameRow(Integer row, Integer... columns) {
        return createMultipleOnSameRow(row, intsToChars(Arrays.stream(columns)).toList());
    }

    public List<List<Position>> create2dGrid(Position start, Position end) {
        return create2dGrid(start.col(), start.row(), end.col(), end.row());
    }

    private Stream<Character> intsToChars(Stream<Integer> ints) {
        return ints.map(this::intToChar);
    }

    private Character intToChar(int i) {
        return (char) i;
    }

    private List<Position> createMultipleOnSameRow(Integer Row, List<Character> columns) {
        return columns.stream().map(col -> create(col, Row)).toList();
    }

    private List<List<Position>> create2dGrid(Integer startX, Integer startY, Integer endX, Integer endY) {
        final List<List<Position>> positions = new ArrayList<>();

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
