package chess.board;

import chess.piece.PieceFactory;
import chess.piece.strategies.PieceStrategyFactory;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private static final SquareFactory squareFactory = new SquareFactory();
    private static final PieceStrategyFactory pieceStrategyFactory = new PieceStrategyFactory();
    private static final PieceFactory pieceFactory = new PieceFactory(pieceStrategyFactory);
    private static final BoardFactory  boardFactory = new BoardFactory(squareFactory, pieceFactory);

    @Test
    public void testToString() {
        final Board board = boardFactory.createStandardBoard();
        System.out.println(board);
    }
}
