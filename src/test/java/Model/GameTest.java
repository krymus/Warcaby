package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game = new Game();


    @Test
    void gameState() {
        assertEquals(0,game.board.gameIsOn);

        assertEquals( PieceType.CHECKER, game.board.Fields[0][0].piece.pieceType);
        assertEquals( Color.WHITE, game.board.Fields[0][0].piece.color);

        assertEquals( PieceType.CHECKER, game.board.Fields[7][7].piece.pieceType);
        assertEquals( Color.BLACK, game.board.Fields[7][7].piece.color);

    }

    @Test
    void isLegalInString() {
    }

    @Test
    void moveInInts() {
        game.moveInInts(2,2,3,3);
        assertEquals( PieceType.CHECKER, game.board.Fields[3][3].piece.pieceType);
        assertEquals( Color.WHITE, game.board.Fields[3][3].piece.color);
        game.moveInInts(5,5,4,4);
        game.moveInInts(3,3,5,5);
        assertEquals( PieceType.CHECKER, game.board.Fields[5][5].piece.pieceType);
        assertEquals( Color.WHITE, game.board.Fields[5][5].piece.color);
    }

    @Test
    void move() {
    }

    @Test
    void startingPosition() {
    }
}