package Model;

public class Game {

    public Board board;

    public Rules rules;

    public Game()
    {
        this.rules = new Rules();
        this.board = new Board(8,8,false);
        this.board.gameIsOn = 0;
        startingPosition();
    }
    public String gameState()
    {
        return board.gameState();
    }

    public boolean isLegalInString(int x, int y, int x2, int y2)
    {
        return rules.isLegal(board, board.Fields[x][y], board.Fields[x2][y2]);
    }

    public void moveInInts(int x, int y, int x2, int y2)
    {
        move(board.Fields[x][y], board.Fields[x2][y2]);
    }
    public void move(Field startField, Field endField)
    {
        System.out.println("Moving: " + startField.x + " " + startField.y + " " + endField.x + " "
                + endField.y);
        System.out.println("Move is legal: " + rules.isLegal(board, startField, endField));
        if(rules.isLegal(board, startField, endField))
        {
            if (board.distance(startField, endField) == 1)
            {
                endField.piece = startField.piece;
                startField.piece = null;
            }
            else if (startField.piece.pieceType == PieceType.CHECKER &&
                    board.distance(startField, endField) == 2)
            {
                endField.piece = startField.piece;
                startField.piece = null;
                int middleFieldX = (startField.x - endField.x)/2 + endField.x;
                int middleFieldY = (startField.y - endField.y)/2 + endField.y;
                board.Fields[middleFieldX][middleFieldY].piece = null;
            }
            else if (startField.piece.pieceType == PieceType.KING)
            {
                if (rules.enemyPiecesOnPath(startField, endField, this.board) == 0)
                {
                    endField.piece = startField.piece;
                    startField.piece = null;
                }
                else
                {
                    rules.getFieldOfEnemyPieceOnPath(startField,endField,board).piece = null;
                    endField.piece = startField.piece;
                    startField.piece = null;
                }
            }

            if (board.distance(startField, endField) == 1 || ((!rules.CHECKERCanAtack(endField, board)) && (!rules.KINGcanAttack(endField, board))))
            {
                board.whiteTurn = !board.whiteTurn;
                if ( endField.y == board.sizeY-1 &&
                        endField.piece.pieceType == PieceType.CHECKER &&
                        endField.piece.color == Color.WHITE) endField.piece.pieceType = PieceType.KING;
                else if ( endField.y == 0 &&
                        endField.piece.pieceType == PieceType.CHECKER &&
                        endField.piece.color == Color.BLACK) endField.piece.pieceType = PieceType.KING;
            }

            if ((!board.whiteTurn) && rules.didBlackLost(board))
            {
                board.gameIsOn = 1;
                System.out.println("WHITE WIN!");
            }
            else if (board.whiteTurn && rules.didWhiteLost(board))
            {
                board.gameIsOn = 2;
                System.out.println("BLACK WIN!");
            }

        }
        else
        {
            System.out.println("BAD MOVE");

        }
    }

    public void startingPosition()
    {
        for(int i = board.sizeY - 3; i < board.sizeY; i++)
        {
            for(int j = 0; j < board.sizeX; j++)
            {
                if (board.Fields[j][i].color == Color.BLACK)
                {
                    board.Fields[j][i].piece = new Piece(Color.BLACK, PieceType.CHECKER);
                }

            }
        }

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < board.sizeX; j++)
            {
                if (board.Fields[j][i].color == Color.BLACK)
                {
                    board.Fields[j][i].piece = new Piece(Color.WHITE, PieceType.CHECKER);
                }

            }
        }

        board.whiteTurn = true;
    }

}