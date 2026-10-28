package chess.board;

import chess.Color;
import chess.Position;
import chess.PositionFactory;
import chess.piece.Piece;
import chess.piece.PieceFactory;
import chess.piece.strategies.PieceStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class BoardTest {
    private static final SquareFactory squareFactory = new SquareFactory();
    private static final PieceStrategyFactory pieceStrategyFactory = new PieceStrategyFactory();
    private static final PieceFactory pieceFactory = new PieceFactory(pieceStrategyFactory);
    private static final PositionFactory positionFactory = new PositionFactory();
    private static final BoardFactory boardFactory = new BoardFactory(squareFactory, pieceFactory, positionFactory);

    private Board board;

    @BeforeEach
    public void resetBoard() {
        board = boardFactory.createStandardBoard();
    }

    @Test
    public void testToString() {
        System.out.println(board);
    }

    @Test
    public void testBounds() {
        assertTrue(board.inBounds(positionFactory.create('A', 1)));
        assertTrue(board.inBounds(positionFactory.create('H', 8)));

        assertFalse(board.inBounds(positionFactory.create('A', 9)));
        assertFalse(board.inBounds(positionFactory.create('I', 1)));
        assertFalse(board.inBounds(positionFactory.create('A', 0)));
        assertFalse(board.inBounds(positionFactory.create('@', 1)));
    }

    @Test
    public void testStandardSquareColors() {
        Color expectedColor = Color.BLACK;
        for (List<Square> squares : board.getSquares()) {
            for (Square square : squares) {
                assertEquals(expectedColor, square.getColor());
                expectedColor = expectedColor.getOpposite();
            }
            expectedColor = expectedColor.getOpposite();
        }
    }

    @Test
    public void testStandardPawnPlacements() {
        final Position startingWhitePosition = positionFactory.create('A', 2);
        final Position startingBlackPosition = positionFactory.create('A', 7);
        final Piece whitePawn = pieceFactory.createPawn(Color.WHITE);
        final Piece blackPawn = pieceFactory.createPawn(Color.BLACK);

        for (int columnOffset = 0; columnOffset < 8; columnOffset++) {
            assertPositionContainsPiece(whitePawn, startingWhitePosition.offset(columnOffset, 0));
            assertPositionContainsPiece(blackPawn, startingBlackPosition.offset(columnOffset, 0));
        }
    }

    @Test
    public void testStandardRookPlacements() {
        final List<Position> whitePositions = List.of(positionFactory.create('A', 1), positionFactory.create('H', 1));
        final List<Position> blackPositions = List.of(positionFactory.create('A', 8), positionFactory.create('H', 8));
        final Piece whiteRook = pieceFactory.createRook(Color.WHITE);
        final Piece blackRook = pieceFactory.createRook(Color.BLACK);

        for (int i = 0; i < whitePositions.size(); i++) {
            assertPositionContainsPiece(whiteRook, whitePositions.get(i));
            assertPositionContainsPiece(blackRook, blackPositions.get(i));
        }
    }

    @Test
    public void testStandardKnightPlacements() {
        final List<Position> whitePositions = List.of(positionFactory.create('B', 1), positionFactory.create('G', 1));
        final List<Position> blackPositions = List.of(positionFactory.create('B', 8), positionFactory.create('G', 8));
        final Piece whiteKnight = pieceFactory.createKnight(Color.WHITE);
        final Piece blackKnight = pieceFactory.createKnight(Color.BLACK);

        for (int i = 0; i < whitePositions.size(); i++) {
            assertPositionContainsPiece(whiteKnight, whitePositions.get(i));
            assertPositionContainsPiece(blackKnight, blackPositions.get(i));
        }
    }

    @Test
    public void testStandardBishopPlacements() {
        final List<Position> whitePositions = List.of(positionFactory.create('C', 1), positionFactory.create('F', 1));
        final List<Position> blackPositions = List.of(positionFactory.create('C', 8), positionFactory.create('F', 8));
        final Piece whiteBishop = pieceFactory.createBishop(Color.WHITE);
        final Piece blackBishop = pieceFactory.createBishop(Color.BLACK);

        for (int i = 0; i < whitePositions.size(); i++) {
            assertPositionContainsPiece(whiteBishop, whitePositions.get(i));
            assertPositionContainsPiece(blackBishop, blackPositions.get(i));
        }
    }

    @Test
    public void testStandardQueenPlacements() {
        final Position whitePosition = positionFactory.create('D', 1);
        final Position blackPosition = positionFactory.create('E', 8);
        final Piece whiteQueen = pieceFactory.createQueen(Color.WHITE);
        final Piece blackQueen = pieceFactory.createQueen(Color.BLACK);

        assertPositionContainsPiece(whiteQueen, whitePosition);
        assertPositionContainsPiece(blackQueen, blackPosition);
    }

    @Test
    public void testStandardKingPlacements() {
        final Position whitePosition = positionFactory.create('E', 1);
        final Position blackPosition = positionFactory.create('D', 8);
        final Piece whiteKing = pieceFactory.createKing(Color.WHITE);
        final Piece blackKing = pieceFactory.createKing(Color.BLACK);

        assertPositionContainsPiece(whiteKing, whitePosition);
        assertPositionContainsPiece(blackKing, blackPosition);
    }

    private void assertPositionContainsPiece(Piece expected, Position position) {
        assertEquals(expected, board.getPiece(position).orElse(null));
    }
}
