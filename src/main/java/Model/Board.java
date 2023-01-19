package Model;

public class Board {

    public Field[][] Fields;
    int sizeX;
    int sizeY;
    boolean A1isWhite;
    public boolean whiteTurn;
    public int gameIsOn; // 1 - game is on 2 - white win 3 - black win


    public Board(int sizeX, int sizeY, boolean A1isWhite)
    {
        this.Fields = new Field[sizeX][sizeY];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.A1isWhite = A1isWhite;
        giveColorsToFields(A1isWhite);
        gameIsOn = 1;
    }


    public String gameState()
    {
        String gameState = "";
        for(int i = sizeY -1 ; i >= 0; i--)
        {
            for(int j = 0; j < sizeX; j++)
            {
                if (Fields[j][i].piece == null) gameState = gameState + "0 ";
                else if (Fields[j][i].piece.color == Color.WHITE)
                {
                    if (Fields[j][i].piece.pieceType == PieceType.CHECKER)
                        gameState = gameState + "1 ";
                    else
                        gameState = gameState + "3 ";
                }
                else
                {
                    if (Fields[j][i].piece.pieceType == PieceType.CHECKER)
                        gameState = gameState + "2 ";
                    else
                        gameState = gameState + "4 ";
                }

            }
        }

        if(whiteTurn)
        {
            gameState = gameState + "1 ";
        }
        else
        {
            gameState = gameState + "2 ";
        }

        switch(gameIsOn)
        {
            case 1:
                return gameState + "1 ";
            case 2:
                return gameState + "2 ";
            default:
                return gameState + "0 ";
        }

    }

    public void displayGameState()
    {
        for(int i = sizeY -1 ; i >= 0; i--)
        {
            for(int j = 0; j < sizeX; j++)
            {
                if (Fields[j][i].piece == null) System.out.print("0 ");
                else if (Fields[j][i].piece.color == Color.WHITE)
                {
                    if (Fields[j][i].piece.pieceType == PieceType.CHECKER)
                        System.out.print("1 ");
                    else
                        System.out.print("3 ");
                }
                else
                {
                    if (Fields[j][i].piece.pieceType == PieceType.CHECKER)
                        System.out.print("2 ");
                    else
                        System.out.print("4 ");
                }

            } System.out.println("");
        }
    }

    public void giveColorsToFields(boolean A1isWhite)
    {
        if(A1isWhite)
        {
            for (int i = 0 ; i < sizeY; i++)
            {
                for ( int j = 0 ; j < sizeX; j++)
                {
                    if ((i+j)%2 == 0) Fields[j][i] = new Field(Color.WHITE,j , i);
                    else Fields[j][i] = new Field(Color.BLACK, j, i);
                }
            }
        }
        else
        {
            for (int i = 0 ; i < sizeY; i++)
            {
                for ( int j = 0 ; j < sizeX; j++)
                {
                    if ((i+j)%2 == 0) Fields[j][i] = new Field(Color.BLACK, j, i);
                    else Fields[j][i] = new Field(Color.WHITE, j, i);
                }
            }
        }
    }

    public boolean validIndex(int x, int y)
    {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) return false;
        else return true;
    }

    public int distance(Field startField, Field endField)
    {
        if(Math.abs(startField.x - endField.x) != Math.abs(startField.y - endField.y)) return -1;
        else return Math.abs(startField.x - endField.x);
    }


}