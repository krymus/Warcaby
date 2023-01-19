package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RulesTest {

    Board board = new Board(8,8, true);
    Rules rules = new Rules();



    @Test
    void isLegal() {
        Board board = testPosition();
        assertEquals(false, rules.isLegal(board, board.Fields[3][4], board.Fields[4][5]));
        assertEquals(false, rules.isLegal(board, board.Fields[1][1], board.Fields[2][2]));
        board.whiteTurn = true;
        assertEquals(false, rules.isLegal(board, board.Fields[2][2], board.Fields[2][2]));
        board.whiteTurn = false;
        assertEquals(false, rules.isLegal(board, board.Fields[1][1], board.Fields[3][3]));
        assertEquals(false, rules.isLegal(board, board.Fields[2][2], board.Fields[0][1]));

        board = testPosition2();
        board.whiteTurn = true;
        assertEquals(true, rules.isLegal(board, board.Fields[1][1], board.Fields[5][5]));
        assertEquals(true, rules.isLegal(board, board.Fields[1][1], board.Fields[6][6]));



    }

    @Test
    void isLegalforCHECKER() {
        Board board = testPosition();
        board.whiteTurn = true;
        assertEquals(true, rules.isLegal(board, board.Fields[1][1], board.Fields[3][3]));
        board.whiteTurn = false;
        assertEquals(false, rules.isLegal(board, board.Fields[1][1], board.Fields[3][3]));
        assertEquals(false, rules.isLegal(board, board.Fields[2][2], board.Fields[3][1]));
        assertEquals(true, rules.isLegal(board, board.Fields[2][2], board.Fields[0][0]));


        board.Fields[5][5].piece = new Piece(Color.BLACK, PieceType.CHECKER);

        assertEquals(false, rules.isLegalforCHECKER(board, board.Fields[5][5], board.Fields[4][4]));
        board.displayGameState();
        board.Fields[2][2].piece = null;

        assertEquals(true, rules.isLegal(board, board.Fields[5][5], board.Fields[4][4]));
    }

    @Test
    void isLegalforKING() {
        Board board = testPosition2();


    }

    @Test
    void isAttackPossible() {
        Board board = testPosition();
        board.whiteTurn = true;
        assertEquals(true, rules.isAttackPossible(board));
        board.whiteTurn = false;
        assertEquals(true, rules.isAttackPossible(board));
    }

    @Test
    void CHECKERCanAtack() {
        Board board = testPosition();
        board.whiteTurn = true;
        assertEquals(true, rules.CHECKERCanAtack(board.Fields[1][1], board));
        board.whiteTurn = false;
        assertEquals(true, rules.CHECKERCanAtack(board.Fields[2][2], board));
    }

    @Test
    void KINGcanAttack() {
    }

    @Test
    void KINGcanAttackinOneOfDiagonals() {
        Board board = testPosition2();

        assertEquals(false, rules.KINGcanAttackinOneOfDiagonals(board.Fields[4][4], board, 1));
        assertEquals(false, rules.KINGcanAttackinOneOfDiagonals(board.Fields[4][4], board, 2));
        assertEquals(true, rules.KINGcanAttackinOneOfDiagonals(board.Fields[4][4], board, 3));
        assertEquals(false, rules.KINGcanAttackinOneOfDiagonals(board.Fields[4][4], board, 4));
    }

    @Test
    void distance() {
    }

    @Test
    void enemyPiecesOnPath() {
    }

    @Test
    void getFieldOfEnemyPieceOnPath() {
        Board board = testPosition2();

        assertEquals(board.Fields[1][1], rules.getFieldOfEnemyPieceOnPath(board.Fields[4][4], board.Fields[0][0], board));
        assertEquals(board.Fields[4][4], rules.getFieldOfEnemyPieceOnPath(board.Fields[1][1], board.Fields[6][6], board));
    }

    @Test
    void didWhiteLost() {
        Board board = testPosition4();
        assertEquals(true, rules.didWhiteLost(board));
        assertEquals(false, rules.didBlackLost(board));
    }

    @Test
    void didBlackLost() {
        Board board = testPosition3();
        assertEquals(true, rules.didBlackLost(board));
        assertEquals(false, rules.didWhiteLost(board));

        board = testPosition();
        assertEquals(false, rules.didWhiteLost(board));
        assertEquals(false, rules.didBlackLost(board));
    }

    @Test
    void pieceCanMove() {
        Board board = testPosition5();
        assertEquals(true, rules.pieceCanMove(board.Fields[1][1], board));
        assertEquals(true, rules.pieceCanMove(board.Fields[4][4], board));
    }


    public Board testPosition()
    {
        Board board = new Board(8,8,true);
        board.Fields[1][1].piece = new Piece(Color.WHITE, PieceType.CHECKER);
        board.Fields[2][2].piece = new Piece(Color.BLACK, PieceType.CHECKER);
        return board;
    }

    public Board testPosition2()
    {
        Board board = new Board(8, 8, true);
        board.Fields[1][1].piece = new Piece(Color.WHITE, PieceType.KING);
        board.Fields[4][4].piece = new Piece(Color.BLACK, PieceType.KING);
        return board;
    }

    public Board testPosition3()
    {
        Board board = new Board(8, 8, true);
        board.Fields[1][1].piece = new Piece(Color.WHITE, PieceType.KING);
        return board;
    }

    public Board testPosition4()
    {
        Board board = new Board(8, 8, true);
        board.Fields[1][1].piece = new Piece(Color.BLACK, PieceType.KING);
        return board;
    }

    public Board testPosition5()
    {
        Board board = new Board(8, 8, true);
        board.Fields[1][1].piece = new Piece(Color.WHITE, PieceType.CHECKER);
        board.Fields[4][4].piece = new Piece(Color.BLACK, PieceType.CHECKER);
        return board;
    }
}