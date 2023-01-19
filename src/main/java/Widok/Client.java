package Widok;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Client class is responsible for interpreting information from ClientConnection and display them as UI.
 */
public class Client extends Application {

    public Stage stageHelp;
    public boolean playerIsWhite;
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private Tile[][] tiles = new Tile[WIDTH][HEIGHT];;

    public Group pieceGroup = new Group();

    private Group tileGroup = new Group();

    public ClientConnection connection;

    public void setConnectionsClient()
    {
        this.connection.client = this;
    }

    private Parent createContent() throws IOException
    {
        Pane pane = new Pane();

        for(int i = 0; i < 8; i++)
        {
            for( int j = 0; j < 8; j++)
            {
                tiles[j][i] = new Tile((j+i)%2==1, j ,i, this);
                tileGroup.getChildren().addAll(tiles[j][i]);
            }
        }
        pane.getChildren().addAll(tileGroup, pieceGroup);
        return pane;
    }

    public void setGameState(String gameState)
    /**
     * method decodes information about gamestate on server and accordingly changes UI.
     * First 64 words mean pieces on the board and rest of them is game status.
     * if game status changes, UI title also changes.
     */
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                String[] coordinates = gameState.split(" ");

                if(coordinates[66].equals("1")) playerIsWhite = true;
                else playerIsWhite = false;

                try
                {
                    for(int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            String coordinate = coordinates[i*8+j];
                            if(playerIsWhite) coordinate = coordinates[i*8+j];
                            else coordinate = coordinates[(7-i)*8+(7-j)];
                            if (coordinate.equals("0") && tiles[j][i].hasPiece())
                            {
                                tiles[j][i].setPiece(null);
                            }
                            else if (coordinate.equals("1") && !(tiles[j][i].hasPiece() &&
                                    tiles[j][i].piece.type == PieceType.WHITE_CHECKER))
                            {
                                tiles[j][i].setPiece(new Piece(PieceType.WHITE_CHECKER, j, i));
                                pieceGroup.getChildren().add(tiles[j][i].piece);
                            }
                            else if (coordinate.equals("2") && !(tiles[j][i].hasPiece() &&
                                    tiles[j][i].piece.type == PieceType.BLACK_CHECKER))
                            {
                                tiles[j][i].setPiece(new Piece(PieceType.BLACK_CHECKER, j, i));
                                pieceGroup.getChildren().add(tiles[j][i].piece);
                            }
                            else if (coordinate.equals("3") && !(tiles[j][i].hasPiece() &&
                                    tiles[j][i].piece.type == PieceType.WHITE_KING))
                            {
                                tiles[j][i].setPiece(new Piece(PieceType.WHITE_KING, j, i));
                                pieceGroup.getChildren().add(tiles[j][i].piece);
                            }
                            else if (coordinate.equals("4") && !(tiles[j][i].hasPiece() &&
                                    tiles[j][i].piece.type == PieceType.BLACK_KING))
                            {
                                tiles[j][i].setPiece(new Piece(PieceType.BLACK_KING, j, i));
                                pieceGroup.getChildren().add(tiles[j][i].piece);
                            }
                        }
                    }
                }catch (Exception e)
                {
                    setGameState(gameState);
                }

                if(!coordinates[65].equals("0")) setGameResult(coordinates[65], coordinates[66]);
                else if(coordinates[64].equals(coordinates[66])) stageHelp.setTitle("Your turn!");
                else stageHelp.setTitle("Enemy turn!");

            }
        });
    }


    public void setGameResult(String whoWin, String id)
    {
        if (whoWin.equals(id))
        {
            stageHelp.setTitle("You win!");
        }
        else stageHelp.setTitle("You lose!");
    }

    public Client() throws IOException
    {
        this.connection = new ClientConnection();
        setConnectionsClient();

    }


    @Override
    public void start(Stage stage) throws Exception {
        stageHelp = stage;
        stage.setTitle("CHECKERS");
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            System.exit(1);
        });
    }

    public static void main(String[] args)  throws IOException {
        launch();
    }

}
