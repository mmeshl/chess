package chess.board;

import chess.Color;
import chess.Position;
import chess.PositionFactory;
import chess.piece.Piece;
import chess.piece.PieceFactory;
import chess.piece.PieceName;

import java.util.List;
import java.util.Map;

public final class BoardFactory {
    private static final Character STANDARD_START_COL = 'A';
    private static final Integer STANDARD_START_ROW = 1;
    private static final Character STANDARD_END_COL = 'H';
    private static final Integer STANDARD_END_ROW = 8;

    private final SquareFactory squareFactory;
    private final PieceFactory pieceFactory;
    private final PositionFactory positionFactory;

    private final List<Position> standardWhitePawnPositions;
    private final List<Position> standardWhiteRookPositions;
    private final List<Position> standardWhiteKnightPositions;
    private final List<Position> standardWhiteBishopPositions;
    private final Position standardWhiteQueenPosition;
    private final Position standardWhiteKingPosition;

    public BoardFactory(SquareFactory squareFactory, PieceFactory pieceFactory, PositionFactory positionFactory) {
        this.squareFactory = squareFactory;
        this.pieceFactory = pieceFactory;
        this.positionFactory = positionFactory;

        this.standardWhitePawnPositions = positionFactory.createHorizontalRange(
                STANDARD_START_ROW + 1, STANDARD_START_COL, STANDARD_END_COL
        );
        this.standardWhiteRookPositions = positionFactory.createMultipleOnSameRow(
                STANDARD_START_ROW, STANDARD_START_COL, STANDARD_END_COL
        );
        this.standardWhiteKnightPositions = positionFactory.createMultipleOnSameRow(
                STANDARD_START_ROW, STANDARD_START_COL + 1, STANDARD_END_COL - 1
        );
        this.standardWhiteBishopPositions = positionFactory.createMultipleOnSameRow(
                STANDARD_START_ROW, STANDARD_START_COL + 2, STANDARD_END_COL - 2
        );
        this.standardWhiteQueenPosition = positionFactory.create(STANDARD_START_COL + 3, STANDARD_START_ROW);
        this.standardWhiteKingPosition = positionFactory.create(STANDARD_END_COL - 3, STANDARD_START_ROW);
    }

    public Board createStandardBoard() {
        final Board board = new Board(squaresFromPositions(getStandardPositions()));
        placeStandardPieces(board);
        return board;
    }

    public Board createSizedBoardWithPieces(Integer width, Integer height, Map<Position, Piece> pieces) {
        final Position start = positionFactory.create('A', 1);
        final Position end = start.offset(width - 1, height - 1);

        final Board board = new Board(squaresFromPositions(positionFactory.create2dGrid(start, end)));
        pieces.forEach(board::placePiece);
        return board;
    }

    public Board createSizedBoardWithPieces(Integer length, Map<Position, Piece> pieces) {
        return createSizedBoardWithPieces(length, length, pieces);
    }

    private List<List<Position>> getStandardPositions() {
        final Position start = positionFactory.create(STANDARD_START_COL, STANDARD_START_ROW);
        final Position end = positionFactory.create(STANDARD_END_COL, STANDARD_END_ROW);

        return positionFactory.create2dGrid(start, end);
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
        placePiecesOnBothSides(board, PieceName.PAWN, standardWhitePawnPositions);
    }

    private void placeStandardRooks(Board board) {
        placePiecesOnBothSides(board, PieceName.ROOK, standardWhiteRookPositions);
    }

    private void placeStandardKnights(Board board) {
        placePiecesOnBothSides(board, PieceName.KNIGHT, standardWhiteKnightPositions);
    }

    private void placeStandardBishops(Board board) {
        placePiecesOnBothSides(board, PieceName.BISHOP, standardWhiteBishopPositions);
    }

    private void placeStandardQueens(Board board) {
        final Position blackPosition = getStandardBlackPositionFromWhite(standardWhiteQueenPosition, +1);
        placePieceOnBothSides(board, PieceName.QUEEN, standardWhiteQueenPosition, blackPosition);
    }

    private void placeStandardKings(Board board) {
        final Position blackPosition = getStandardBlackPositionFromWhite(standardWhiteKingPosition, -1);
        placePieceOnBothSides(board, PieceName.KING, standardWhiteKingPosition, blackPosition);
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
        return whitePosition.oppositeRow(columnOffset, STANDARD_END_ROW);
    }

    private Position getStandardBlackPositionFromWhite(Position whitePosition) {
        return getStandardBlackPositionFromWhite(whitePosition,0);
    }
}
