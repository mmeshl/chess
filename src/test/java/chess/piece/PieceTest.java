package chess.piece;

import chess.Color;
import chess.Position;
import chess.PositionFactory;
import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.SquareFactory;
import chess.piece.strategies.PieceStrategyFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class PieceTest {
    protected static final SquareFactory squareFactory = new SquareFactory();
    protected static final PieceStrategyFactory pieceStrategyFactory = new PieceStrategyFactory();
    protected static final PieceFactory pieceFactory = new PieceFactory(pieceStrategyFactory);
    protected static final PositionFactory positionFactory = new PositionFactory();
    protected static final BoardFactory boardFactory = new BoardFactory(squareFactory, pieceFactory, positionFactory);

    protected static void testPiecesEqual(PieceName pieceName) {
        assertEquals(pieceFactory.createPiece(pieceName, Color.WHITE), pieceFactory.createPiece(pieceName, Color.WHITE));
        assertNotEquals(pieceFactory.createPiece(pieceName, Color.WHITE), pieceFactory.createPiece(pieceName, Color.BLACK));
    }

    protected static void testMoves(Board board, Position position, List<Position> expectedMoves) {
        assertTrue(board.hasPiece(position));
        final Piece piece = board.getPiece(position).orElseThrow();
        final List<Position> actualMoves = piece.getValidMoves(position, board);

        assertEquals(expectedMoves.size(), actualMoves.size());
        for (Position element : expectedMoves) {
            assertTrue(actualMoves.contains(element));
        }
    }
}
