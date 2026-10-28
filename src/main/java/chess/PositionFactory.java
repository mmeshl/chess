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

    public List<Position> createExclusiveRange(Position start, Position end) {
        final List<Integer> columns = createExclusiveIntRange(start.col(), end.col());
        final List<Integer> rows = createExclusiveIntRange(start.row(), end.row());

        if (columns.size() == 1) {
            return createVerticalRange(columns.getFirst(), rows);
        } else if (rows.size() == 1) {
            return createHorizontalRange(columns, rows.getFirst());
        } else if (columns.size() == rows.size()) {
            return createDiagonalRange(columns, rows);
        } else {
            throw new IllegalStateException();
        }
    }

    private List<Position> createHorizontalRange(List<Integer> columns, Integer row) {
        return columns.stream().map(col -> new Position(col, row)).toList();
    }

    private List<Position> createVerticalRange(Integer col, List<Integer> rows) {
        return rows.stream().map(row -> new Position(col, row)).toList();
    }

    private List<Position> createDiagonalRange(List<Integer> columns, List<Integer> rows) {
        assert columns.size() == rows.size();

        final List<Position> positions = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            positions.add(new Position(columns.get(i), rows.get(i)));
        }
        return positions;
    }

    private List<Integer> createExclusiveIntRange(Integer start, Integer end) {
        if (start.equals(end)) {
            return List.of(start);
        } else if (start > end) {
            start ^= end;
            end ^= start;
            start ^= end;
        }

        return IntStream.range(start + 1, end).boxed().toList();
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
