package chess.board;

import chess.Color;
import chess.Position;
import chess.piece.PieceFactory;
import chess.piece.PieceName;

import java.util.List;
import java.util.stream.IntStream;

public final class BoardFactory {
    private static final Character STANDARD_START_COL = 'A';
    private static final Integer STANDARD_START_ROW = 1;
    private static final Character STANDARD_END_COL = 'H';
    private static final Integer STANDARD_END_ROW = 8;

    private static final List<Position> STANDARD_WHITE_PAWN_POSITIONS =
            IntStream.rangeClosed(STANDARD_START_COL, STANDARD_END_COL)
                    .mapToObj(col -> (char) col)
                    .map(col -> new Position(col, 2))
                    .toList();

    private static final List<Position> STANDARD_WHITE_ROOK_POSITIONS =
            List.of(new Position('A', 1), new Position('H', 1));

    private static final List<Position> STANDARD_WHITE_KNIGHT_POSITIONS =
            List.of(new Position('B', 1), new Position('G', 1));

    private static final List<Position> STANDARD_WHITE_BISHOP_POSITIONS =
            List.of(new Position('C', 1), new Position('F', 1));

    private static final Position STANDARD_WHITE_QUEEN_POSITION = new Position('D', 1);
    private static final Position STANDARD_WHITE_KING_POSITION = new Position('E', 1);

    private final SquareFactory squareFactory;
    private final PieceFactory pieceFactory;

    public BoardFactory(SquareFactory squareFactory, PieceFactory pieceFactory) {
        this.squareFactory = squareFactory;
        this.pieceFactory = pieceFactory;
    }

    public Board createStandardBoard() {
        final Board board = new Board(squaresFromPositions(getStandardPositions()));
        placeStandardPieces(board);
        return board;
    }

    private List<List<Position>> getStandardPositions() {
        final Position start = new Position(STANDARD_START_COL, STANDARD_START_ROW);
        final Position end = new Position(STANDARD_END_COL, STANDARD_END_ROW);

        return Position.range(start, end);
    }

    private List<List<Square>> squaresFromPositions(List<List<Position>> positions) {
        return positions.stream()
                .map(row -> row.stream().map(position -> {
                    if ((position.col() + position.row()) % 2 == 0) {
                        return squareFactory.createBlack();
                    } else {
                        return squareFactory.createWhite();
                    }
                }).toList())
                .toList();
    }

    private void placeStandardPieces(Board board) {
        placeStandardPawns(board);
        placeStandardRooks(board);
        placeStandardKnights(board);
        placeStandardBishops(board);
        placeStandardQueens(board);
        placeStandardKings(board);
    }

    private void placeStandardPawns(Board board) {
        placePiecesOnBothSides(board, PieceName.PAWN, STANDARD_WHITE_PAWN_POSITIONS);
    }

    private void placeStandardRooks(Board board) {
        placePiecesOnBothSides(board, PieceName.ROOK, STANDARD_WHITE_ROOK_POSITIONS);
    }

    private void placeStandardKnights(Board board) {
        placePiecesOnBothSides(board, PieceName.KNIGHT, STANDARD_WHITE_KNIGHT_POSITIONS);
    }

    private void placeStandardBishops(Board board) {
        placePiecesOnBothSides(board, PieceName.BISHOP, STANDARD_WHITE_BISHOP_POSITIONS);
    }

    private void placeStandardQueens(Board board) {
        final Position blackPosition = getStandardBlackPositionFromWhite(STANDARD_WHITE_QUEEN_POSITION, +1);
        placePieceOnBothSides(board, PieceName.QUEEN, STANDARD_WHITE_QUEEN_POSITION, blackPosition);
    }

    private void placeStandardKings(Board board) {
        final Position blackPosition = getStandardBlackPositionFromWhite(STANDARD_WHITE_KING_POSITION, -1);
        placePieceOnBothSides(board, PieceName.KING, STANDARD_WHITE_KING_POSITION, blackPosition);
    }

    private void placePiecesOnBothSides(Board board, PieceName pieceName, List<Position> whitePositions) {
        for (Position position : whitePositions) {
            placePieceOnBothSides(board, pieceName, position);
        }
    }

    private void placePieceOnBothSides(Board board, PieceName pieceName, Position whitePosition, Position blackPosition) {
        board.placePiece(whitePosition, pieceFactory.createPiece(pieceName, Color.WHITE));
        board.placePiece(blackPosition, pieceFactory.createPiece(pieceName, Color.BLACK));
    }

    private void placePieceOnBothSides(Board board, PieceName pieceName, Position whitePosition) {
        final Position blackPosition = getStandardBlackPositionFromWhite(whitePosition);
        placePieceOnBothSides(board, pieceName, whitePosition, blackPosition);
    }

    private Position getStandardBlackPositionFromWhite(Position whitePosition, Integer columnOffset) {
        return new Position(whitePosition.col() + columnOffset, STANDARD_END_ROW - whitePosition.row() - 1);
    }

    private Position getStandardBlackPositionFromWhite(Position whitePosition) {
        return getStandardBlackPositionFromWhite(whitePosition,0);
    }
}
