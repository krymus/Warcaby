package Widok;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static Widok.Client.TILE_SIZE;

public class Piece extends StackPane {
    PieceType type;
    private double oldX, oldY;

    public Piece(PieceType type, int x, int y)
    {
        this.type = type;

        move(x, y);

        if (type == PieceType.WHITE_CHECKER)
        {
            Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            bg.setFill(Color.valueOf("000000"));

            bg.setStroke(Color.valueOf("000000"));
            bg.setStrokeWidth(TILE_SIZE * 0.03);

            bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

            Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            ellipse.setFill(Color.valueOf("#fcf9f9"));
            ellipse.setStroke(Color.BLACK);
            ellipse.setStrokeWidth(TILE_SIZE * 0.03);

            ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

            getChildren().addAll(bg, ellipse);
        }
        else if (type == PieceType.BLACK_CHECKER)
        {
            Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            bg.setFill(Color.valueOf("000000"));
            bg.setStroke(Color.valueOf("000000"));
            bg.setStrokeWidth(TILE_SIZE * 0.03);
            bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

            Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            ellipse.setFill(Color.valueOf("#4d4d4d"));
            ellipse.setStroke(Color.BLACK);
            ellipse.setStrokeWidth(TILE_SIZE * 0.03);

            ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);
            getChildren().addAll(bg, ellipse);
        }
        else if (type == PieceType.WHITE_KING)
        {
            Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            bg.setFill(Color.valueOf("000000"));
            bg.setStroke(Color.valueOf("000000"));
            bg.setStrokeWidth(TILE_SIZE * 0.03);
            bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.1);

            Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            ellipse.setFill(Color.valueOf("#fcf9f9"));
            ellipse.setStroke(Color.BLACK);
            ellipse.setStrokeWidth(TILE_SIZE * 0.03);
            ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.03);

            Ellipse bg2 = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            bg2.setFill(Color.valueOf("000000"));
            bg2.setStroke(Color.valueOf("000000"));
            bg2.setStrokeWidth(TILE_SIZE * 0.03);
            bg2.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            bg2.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 - TILE_SIZE * 0.06);

            Ellipse ellipse2 = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            ellipse2.setFill(Color.valueOf("#fcf9f9"));
            ellipse2.setStroke(Color.BLACK);
            ellipse2.setStrokeWidth(TILE_SIZE * 0.05);
            ellipse2.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            ellipse2.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 - TILE_SIZE * 0.1);

            getChildren().addAll(bg, ellipse, bg2, ellipse2);
        }

        else if (type == PieceType.BLACK_KING)
        {
            Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            bg.setFill(Color.valueOf("000000"));
            bg.setStroke(Color.valueOf("000000"));
            bg.setStrokeWidth(TILE_SIZE * 0.03);
            bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.1);

            Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            ellipse.setFill(Color.valueOf("#4d4d4d"));
            ellipse.setStroke(Color.BLACK);
            ellipse.setStrokeWidth(TILE_SIZE * 0.03);
            ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.03);

            Ellipse bg2 = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            bg2.setFill(Color.valueOf("000000"));
            bg2.setStroke(Color.valueOf("000000"));
            bg2.setStrokeWidth(TILE_SIZE * 0.03);
            bg2.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            bg2.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 - TILE_SIZE * 0.06);

            Ellipse ellipse2 = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
            ellipse2.setFill(Color.valueOf("#4d4d4d"));
            ellipse2.setStroke(Color.BLACK);
            ellipse2.setStrokeWidth(TILE_SIZE * 0.05);
            ellipse2.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
            ellipse2.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 - TILE_SIZE * 0.1);

            getChildren().addAll(bg, ellipse, bg2, ellipse2);
        }



    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }
}
