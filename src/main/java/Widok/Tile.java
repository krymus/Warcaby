package Widok;


import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Tile extends Rectangle {
    public Piece piece;
    private Client client;

    public int x,y;

    public static Tile firstChosenTile;
    public static Tile secondChosenTile;

    public boolean hasPiece()
    {
        return piece != null;
    }

    public void setPiece(Piece piece)
    {
        if(this.piece != null) client.pieceGroup.getChildren().remove(this.piece);
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y, Client client) throws IOException
    {
        this.client = client;
        setWidth(Client.TILE_SIZE);
        setHeight(Client.TILE_SIZE);
        this.x = x;
        this.y = y;

        relocate(x * Client.TILE_SIZE, y * Client.TILE_SIZE);

        setFill(light ? Color.valueOf("FFFF99") : Color.valueOf("4C9900"));

        this.setOnMouseClicked(e -> {
            e.consume();
            if (firstChosenTile == null)
            {
                firstChosenTile = this;
                this.setFill(Color.valueOf("f11c40"));
            }
            else if (firstChosenTile == this)
            {
                setFill((x+y)%2 == 1 ? Color.valueOf("FFFF99") : Color.valueOf("4C9900"));
                firstChosenTile = null;
            }
            else
            {
                this.setFill(Color.valueOf("f11c40"));

                if(client.playerIsWhite)
                {
                    int yToggled = 7 - firstChosenTile.y;
                    int y2Toggled = 7 - this.y;
                    String move = firstChosenTile.x + " " + yToggled + " " + this.x + " " + y2Toggled;
                    try {
                        client.connection.sendMoves(move);
                    } catch (IOException exception) {}
                }
                else
                {
                    int xToggled = 7 - firstChosenTile.x;
                    int x2Toggled = 7 - this.x;
                    String move = xToggled + " " + firstChosenTile.y + " " + x2Toggled + " " + this.y;
                    try {
                        client.connection.sendMoves(move);
                    } catch (IOException exception) {}
                }


                firstChosenTile.setFill((firstChosenTile.x+ firstChosenTile.y)%2 == 1 ? Color.valueOf("FFFF99") : Color.valueOf("4C9900"));
                setFill((this.x+this.y)%2 == 1 ? Color.valueOf("FFFF99") : Color.valueOf("4C9900"));
                firstChosenTile = null;
                secondChosenTile = null;
            }
        });
    }

}
