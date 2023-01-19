package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void BoardTest()
    {
        Board board = new Board(8,8,true);
        assertEquals(8, board.sizeX);
        assertEquals(8, board.sizeY);
        assertEquals(Color.WHITE, board.Fields[0][0].color);
        assertEquals(0, board.gameIsOn);

        assertEquals(true, board.validIndex(0, 0));
        assertEquals(true, board.validIndex(7, 7));
        assertEquals(true, board.validIndex(0, 7));
        assertEquals(false, board.validIndex(-1, 1));
        assertEquals(false, board.validIndex(0, -2));
        assertEquals(false, board.validIndex(8, 0));
        assertEquals(false, board.validIndex(0, 8));

        assertEquals(1, board.distance(board.Fields[0][0], board.Fields[1][1]));
        assertEquals(-1, board.distance(board.Fields[0][0], board.Fields[1][0]));
        assertEquals(-1, board.distance(board.Fields[0][0], board.Fields[0][1]));
        assertEquals(3, board.distance(board.Fields[0][0], board.Fields[3][3]));

        board.Fields[1][1].piece = new Piece(Color.WHITE, PieceType.KING);
        board.Fields[2][2].piece = new Piece(Color.BLACK, PieceType.KING);
        board.Fields[3][3].piece = new Piece(Color.WHITE, PieceType.CHECKER);
        board.Fields[4][4].piece = new Piece(Color.BLACK, PieceType.CHECKER);

        assertEquals(Color.WHITE, board.Fields[1][1].piece.color);
        assertEquals(PieceType.KING, board.Fields[1][1].piece.pieceType);

        assertEquals(Color.BLACK, board.Fields[2][2].piece.color);
        assertEquals(PieceType.KING, board.Fields[2][2].piece.pieceType);

        assertEquals(Color.WHITE, board.Fields[3][3].piece.color);
        assertEquals(PieceType.CHECKER, board.Fields[3][3].piece.pieceType);

        assertEquals(Color.BLACK, board.Fields[4][4].piece.color);
        assertEquals(PieceType.CHECKER, board.Fields[4][4].piece.pieceType);


    }

}