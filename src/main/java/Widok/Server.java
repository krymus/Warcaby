package Widok;

import Model.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private ServerSocket serverSocket;
    Socket white;
    BufferedReader inW;
    BufferedWriter outW;
    Socket black;
    BufferedReader inB;
    BufferedWriter outB;

    public Server(ServerSocket serverSocket) throws IOException
    {
        this.serverSocket = serverSocket;
        System.out.println("Waiting for players to connect...");
        this.white = serverSocket.accept();
        inW = new BufferedReader(new InputStreamReader(white.getInputStream()));
        outW = new BufferedWriter(new OutputStreamWriter(white.getOutputStream()));


        this.black = serverSocket.accept();
        inB = new BufferedReader(new InputStreamReader(black.getInputStream()));
        outB = new BufferedWriter(new OutputStreamWriter(black.getOutputStream()));

    }

    public void startTheGame() throws IOException
    {
        Game game = new Game();
        String move = "";
        System.out.println(game.gameState());
        broadcastGameState(game.gameState());

        while(game.board.gameIsOn == 0)
        {
            if(game.board.whiteTurn) move = inW.readLine();
            else move = inB.readLine();
            String[] splitMove = move.split(" ");
            int x = Integer.parseInt(splitMove[0]);
            int y = Integer.parseInt(splitMove[1]);
            int x2 = Integer.parseInt(splitMove[2]);
            int y2 = Integer.parseInt(splitMove[3]);
            System.out.println("Attack possible: " + game.rules.isAttackPossible(game.board));
            System.out.println("WhiteTurn: " + game.board.whiteTurn);
            if (game.isLegalInString(x, y, x2, y2))
            {
                game.moveInInts(x, y, x2, y2);
                game.board.displayGameState();
                broadcastGameState(game.gameState());
            }
            else System.out.println("Illegal move");


            System.out.println("");
        }

        broadcastGameState(game.gameState());


    }


    public void listenToWhite()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    while(true)
                    {
                        String msg = inW.readLine();

                        //try to update game using msg from white
                        //if gamestate updated v
                        //broadcastGameState();
                    }
                } catch (IOException e)
                {
                    System.err.println("Problem with White's connection...");
                }

            }
        }).start();
    }

    public void listenToBlack()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    while(true)
                    {
                        String msg = inB.readLine();
                        //try to update game using msg from black
                        //if gamestate updated v

                    }
                } catch (IOException e)
                {
                    System.err.println("Problem with Black's connection...");
                }

            }
        }).start();
    }



    public void broadcastGameState(String gameState) throws IOException
    {
        outW.write(gameState + "1 ");
        outW.newLine();
        outW.flush();

        outB.write(gameState + "2 ");
        outB.newLine();
        outB.flush();
    }


    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startTheGame();

       /* while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String move = scanner.nextLine();
            String[] splitMove = move.split(" ");
            int x = Integer.parseInt(splitMove[0]);
            int y = Integer.parseInt(splitMove[1]);
            int x2 = Integer.parseInt(splitMove[2]);
            int y2 = Integer.parseInt(splitMove[3]);
        } */

    }
}
