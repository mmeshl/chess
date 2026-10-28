package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public final class PawnTest extends PieceTest {
    @Test
    public void testEquals() {
        testPiecesEqual(PieceName.PAWN);
    }

    @Test
    public void testNonCapturingStartingMoves() {
        final Position position = positionFactory.create('B', 1);
        final Map<Position, Piece> pieces = Map.of(position, pieceFactory.createPawn(Color.WHITE));
        final Board board = boardFactory.createSizedBoardWithPieces(3, pieces);

        final List<Position> expectedMoves = List.of(
                position.offset(0, +1),
                position.offset(0, +2)
        );
        testMoves(board, position, expectedMoves);
    }

    @Test
    public void testAllStartingMoves() {
        final Position position = positionFactory.create('B', 1);
        final Map<Position, Piece> pieces = Map.of(
                position, pieceFactory.createPawn(Color.WHITE),
                position.offset(+1, +1), pieceFactory.createPawn(Color.BLACK),
                position.offset(-1, +1), pieceFactory.createPawn(Color.BLACK)
        );
        final Board board = boardFactory.createSizedBoardWithPieces(3, pieces);

        final List<Position> expectedMoves = List.of(
                position.offset(0, +1),
                position.offset(0, +2),
                position.offset(+1, +1),
                position.offset(-1, +1)
        );
        testMoves(board, position, expectedMoves);
    }

    @Test
    public void testNonCapturingNonStartingMoves() {
        final Position startingPosition = positionFactory.create('B', 1);
        final Position currentPosition = startingPosition.offset(0, +1);
        final Piece piece = pieceFactory.createPawn(Color.WHITE);
        final Board board = boardFactory.createSizedBoardWithPieces(3, Map.of(startingPosition, piece));

        assertEquals(Optional.empty(), piece.move(startingPosition, currentPosition, board));
        testMoves(board, currentPosition, List.of(currentPosition.offset(0, +1)));
    }

    @Test
    public void testAllNonStartingMoves() {
        final Position startingPosition = positionFactory.create('B', 1);
        final Position currentPosition = startingPosition.offset(0, +1);
        final Piece piece = pieceFactory.createPawn(Color.WHITE);
        final Map<Position, Piece> pieces = Map.of(
                startingPosition, piece,
                currentPosition.offset(+1, +1), pieceFactory.createPawn(Color.BLACK),
                currentPosition.offset(-1, +1), pieceFactory.createPawn(Color.BLACK)
        );
        final Board board = boardFactory.createSizedBoardWithPieces(3, pieces);

        assertEquals(Optional.empty(), piece.move(startingPosition, currentPosition, board));
        final List<Position> expectedMoves = List.of(
                currentPosition.offset(0, +1),
                currentPosition.offset(+1, +1),
                currentPosition.offset(-1, +1)
        );
        testMoves(board, currentPosition, expectedMoves);
    }

    @Test
    public void testSameColorBlocking() {
        testBlockingPiece(Color.WHITE);
    }

    @Test
    public void testOppositeColorBlocking() {
        testBlockingPiece(Color.BLACK);
    }

    private void testBlockingPiece(Color blockingColor) {
        final Position position = positionFactory.create('B', 1);
        final Map<Position, Piece> pieces = Map.of(
                position, pieceFactory.createPawn(Color.WHITE),
                position.offset(0, +1), pieceFactory.createPawn(blockingColor)
        );
        final Board board = boardFactory.createSizedBoardWithPieces(3, pieces);

        testMoves(board, position, List.of());
    }
}
